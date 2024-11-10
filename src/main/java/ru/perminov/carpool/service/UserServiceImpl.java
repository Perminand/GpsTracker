package ru.perminov.carpool.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.perminov.carpool.dto.users.UserDto;
import ru.perminov.carpool.dto.users.UserDtoOut;
import ru.perminov.carpool.mapper.UserMapper;
import ru.perminov.carpool.model.Role;
import ru.perminov.carpool.model.User;
import ru.perminov.carpool.repository.RoleRepository;
import ru.perminov.carpool.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserDtoOut create(UserDto userDto) {
        User user = UserMapper.toEntity(userDto);
        if (userDto.getRoles() == null) {
            userDto.setRoles("ROLE_USER");
        }
        Role role = roleRepository.findByName(userDto.getRoles()).orElseThrow(() -> new EntityNotFoundException("Roles not found"));
        user.getRoles().add(role);
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        return UserMapper.toDto(user);
    }

    @Override
    public List<UserDtoOut> getAll() {
        return userRepository.findAll().stream().map(UserMapper::toDto).toList();
    }
}
