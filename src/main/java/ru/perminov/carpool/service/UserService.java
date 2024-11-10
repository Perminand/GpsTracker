package ru.perminov.carpool.service;

import ru.perminov.carpool.dto.users.UserDto;
import ru.perminov.carpool.dto.users.UserDtoOut;

public interface UserService {
    UserDtoOut create(UserDto userDto);
}
