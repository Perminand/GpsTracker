package ru.perminov.carpool.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.perminov.carpool.client.ClientWialon;
import ru.perminov.carpool.dto.users.UserDtoForItems;
import ru.perminov.carpool.dto.users.UserDtoOut;
import ru.perminov.carpool.dto.users.UserDtoWeb;
import ru.perminov.carpool.exceptions.errors.ConflictException;
import ru.perminov.carpool.exceptions.errors.EntityNotFoundException;
import ru.perminov.carpool.mapper.UserMapper;
import ru.perminov.carpool.model.Role;
import ru.perminov.carpool.model.TokenAccess;
import ru.perminov.carpool.model.TokenWialon;
import ru.perminov.carpool.model.User;
import ru.perminov.carpool.repository.RoleRepository;
import ru.perminov.carpool.repository.TokenAccessRepository;
import ru.perminov.carpool.repository.TokenWealonRepository;
import ru.perminov.carpool.repository.UserRepository;
import ru.perminov.carpool.service.jwt.JwtService;

import java.net.URISyntaxException;
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
    private final TokenWealonRepository tokenWealonRepository;
    private final JwtService jwtService;
    private final ClientWialon client;

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
        var jwt = jwtService.generateToken(user);
        TokenAccess tokenAccess = TokenAccess.builder()
                .name(jwt)
                .endData(LocalDate.now().plusDays(30))
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

        if(!userDto.getEmail().equals(user.getEmail()) || userDto.getEmail() == null) {
            user.setEmail(userDto.getEmail());
        }
        if(userDto.getTokenAccess() != null) {
            LocalDate dateTime = LocalDate.parse(userDto.getTokenAccess(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            if (!dateTime.isEqual(user.getTokenAccess().getEndData())) {
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
    public TokenWialon createWealon(TokenWialon tokenWialon) {
        return tokenWealonRepository.save(tokenWialon);
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
    public void getTokenWialon(User user) {
        String wialonJwt;
        if (user.getTokenWialon() == null) {
            TokenWialon tokenWialon = new TokenWialon();
            try {
                wialonJwt = client.getToken(user.getUsername(), user.getRealPassword());
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ArrayIndexOutOfBoundsException("Нет ключа");
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            tokenWialon.setName(wialonJwt);
            tokenWialon.setDataCreated(LocalDate.now());
            tokenWialon.setEndData(LocalDate.now().plusDays(30));
            createWealon(tokenWialon);
            user.setTokenWialon(tokenWialon);
            create(user);
        }
    }

    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

}


