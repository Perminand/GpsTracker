package ru.perminov.carpool.service.user;

import ru.perminov.carpool.dto.users.UserDto;
import ru.perminov.carpool.dto.users.UserDtoOut;
import ru.perminov.carpool.dto.users.UserUpdateDto;
import ru.perminov.carpool.model.User;

import java.util.List;

public interface UserService {
    User create(UserDto userDto);

    List<UserDtoOut> getAll();

    void update(Long id, UserUpdateDto userDto);

    UserDtoOut getById(Long id);
}
