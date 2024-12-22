package ru.perminov.carpool.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.perminov.carpool.dto.users.UserDtoForItems;
import ru.perminov.carpool.dto.users.UserDtoOut;
import ru.perminov.carpool.dto.users.UserDtoWeb;
import ru.perminov.carpool.exceptions.errors.ConflictException;
import ru.perminov.carpool.exceptions.errors.EntityNotFoundException;
import ru.perminov.carpool.mapper.UserMapper;
import ru.perminov.carpool.model.Role;
import ru.perminov.carpool.model.TokenAccess;
import ru.perminov.carpool.model.User;
import ru.perminov.carpool.repository.RoleRepository;
import ru.perminov.carpool.repository.TokenAccessRepository;
import ru.perminov.carpool.repository.UserRepository;
import ru.perminov.carpool.service.jwt.JwtService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;
    private final TokenAccessRepository tokenAccessRepository;
    private final JwtService jwtService;

    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */

    public User create(UserDtoWeb userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new ConflictException("Пользователь с таким именем уже существует");
        }

        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new ConflictException("Пользователь с таким email уже существует");
        }

        User user = UserMapper.toEntity(userDto);


        Role role = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new EntityNotFoundException("Roles not found"));
        user.getRoles().add(role);
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getRealPassword()));
        user.setRealPassword(userDto.getRealPassword());
        user.setCreatedAt(LocalDateTime.now());
        LocalDate localDate = LocalDate.now().plusDays(30);
        var jwt = jwtService.generateToken(user, localDate);
        TokenAccess tokenAccess = TokenAccess.builder()
                .name(jwt)
                .endData(localDate)
                .dataCreated(LocalDate.now()).build();
        tokenAccessRepository.save(tokenAccess);
        user.setTokenAccess(tokenAccess);
        return userRepository.save(user);
    }

    @Override
    public List<UserDtoOut> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::toDto).toList();
    }

    @Override
    public void update(Long id, UserDtoWeb userDto) {
        User user = userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Пользователь не найден"));

        if(!userDto.getUsername().equals(user.getUsername()) || userDto.getUsername() == null) {
            user.setUsername(userDto.getUsername());
        }

        if (userDto.getEmail() == null || !userDto.getEmail().equals(user.getEmail())) {
            user.setEmail(userDto.getEmail());
        }
        if(userDto.getTokenAccess() != null) {
            LocalDate dateTime = LocalDate.parse(userDto.getTokenAccess(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            if (!dateTime.isEqual(user.getTokenAccess().getEndData())) {
                tokenAccessRepository.deleteById(user.getTokenAccess().getId());
                var jwt = jwtService.generateToken(user, dateTime);
                TokenAccess tokenAccess = TokenAccess.builder()
                        .name(jwt)
                        .endData(dateTime)
                        .dataCreated(LocalDate.now()).build();
                tokenAccessRepository.save(tokenAccess);
                user.getTokenAccess().setEndData(dateTime);
            }
        }
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public UserDtoOut getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Пользователь не найден"));
        return UserMapper.toDto(user);
    }

    @Override
    public UserDtoForItems getUserDtoByUsername(String name) {
        return UserMapper.toDtoForItems(userRepository.findByUsername(name).orElseThrow(()-> new EntityNotFoundException("Пользователь не найден")));
    }


    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

    }

    @Override
    public void create(User user) {
        userRepository.save(user);
    }


    @Override
    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

}


