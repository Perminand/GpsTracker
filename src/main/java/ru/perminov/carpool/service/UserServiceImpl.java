package ru.perminov.carpool.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.perminov.carpool.dto.UserDto;
import ru.perminov.carpool.dto.UserDtoOut;
import ru.perminov.carpool.mapper.UserMapper;
import ru.perminov.carpool.model.User;
import ru.perminov.carpool.repository.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDtoOut create(UserDto userDto) {
        User user = UserMapper.toEntity(userDto);
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        return UserMapper.toDto(user);
    }
}
