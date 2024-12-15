package ru.perminov.carpool.mapper;

import ru.perminov.carpool.dto.users.UserDto;
import ru.perminov.carpool.dto.users.UserDtoOut;
import ru.perminov.carpool.model.User;

import java.util.ArrayList;

public class UserMapper {
    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setRoles(new ArrayList<>());
        return user;
    }

    public static UserDtoOut toDto(User user) {
        return UserDtoOut.builder()
                .id(user.getId())
                .username(user.getUsername())
                .realPassword(user.getRealPassword())
                .email(user.getEmail())
                .roles(user.getRoles())
                .tokenAccess(user.getTokenAccess())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
