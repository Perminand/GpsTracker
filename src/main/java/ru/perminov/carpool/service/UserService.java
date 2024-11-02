package ru.perminov.carpool.service;

import ru.perminov.carpool.dto.UserDto;
import ru.perminov.carpool.dto.UserDtoOut;

public interface UserService {
    UserDtoOut create(UserDto userDto);
}
