package ru.perminov.carpool.service.user;

import ru.perminov.carpool.dto.users.UserDto;
import ru.perminov.carpool.dto.users.UserDtoOut;

import java.util.List;

public interface UserService {
    UserDtoOut create(UserDto userDto);

    List<UserDtoOut> getAll();
}
