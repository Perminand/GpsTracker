package ru.perminov.carpool.service.user;

import ru.perminov.carpool.dto.users.UserDtoForItems;
import ru.perminov.carpool.dto.users.UserDtoOut;
import ru.perminov.carpool.dto.users.UserDtoWeb;
import ru.perminov.carpool.model.User;

import java.util.List;

public interface UserService {
    User create(UserDtoWeb userDto);

    List<UserDtoOut> getAll();

    void update(Long id, UserDtoWeb userDto);

    UserDtoOut getById(Long id);

    UserDtoForItems getUserDtoByUsername(String name);

    User getByUsername(String username);

    void create(User user);

    User getCurrentUser();
}
