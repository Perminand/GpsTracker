package ru.perminov.carpool.mapper;

import ru.perminov.carpool.dto.UserDto;
import ru.perminov.carpool.dto.UserDtoOut;
import ru.perminov.carpool.model.User;

public class UserMapper {
    public static User toEntity (UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        return user;
    }

    public static UserDtoOut toDto(User user) {
        return UserDtoOut.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .build();
    }
}
