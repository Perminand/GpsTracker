package ru.perminov.carpool.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.perminov.carpool.dto.users.UserDto;
import ru.perminov.carpool.dto.users.UserDtoOut;
import ru.perminov.carpool.exceptions.errors.ConflictException;
import ru.perminov.carpool.exceptions.errors.EntityNotFoundException;
import ru.perminov.carpool.mapper.UserMapper;
import ru.perminov.carpool.model.Role;
import ru.perminov.carpool.model.User;
import ru.perminov.carpool.repository.RoleRepository;
import ru.perminov.carpool.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */

    public User create(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new ConflictException("Пользователь с таким именем уже существует");
        }

        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new ConflictException("Пользователь с таким email уже существует");
        }

        User user = UserMapper.toEntity(userDto);
        if (userDto.getRoles() == null) {
            userDto.setRoles("ROLE_USER");
        }
        Role role = roleRepository.findByName(userDto.getRoles()).orElseThrow(() -> new EntityNotFoundException("Roles not found"));
        user.getRoles().add(role);
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public List<UserDtoOut> getAll() {
        return userRepository.findAll().stream().map(UserMapper::toDto).toList();
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

    }

    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

}


