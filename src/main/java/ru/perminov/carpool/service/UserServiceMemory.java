package ru.perminov.carpool.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.perminov.carpool.dto.UserDto;
import ru.perminov.carpool.dto.UserDtoOut;
import ru.perminov.carpool.mapper.UserMapper;
import ru.perminov.carpool.model.User;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceMemory implements UserService{
    private Long id = 1L;
    private Map<Long, User> users = new HashMap<>();
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDtoOut create(UserDto userDto) {
        User user = UserMapper.toEntity(userDto);
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setId(getId());
        users.put(user.getId(), user);
        return UserMapper.toDto(user);
    }
    private Long getId() {
        return id++;
    }
}
