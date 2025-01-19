package ru.perminov.carpool.service.user;

import ru.perminov.carpool.dto.users.UserDtoForMessages;
import ru.perminov.carpool.dto.users.UserDtoOut;
import ru.perminov.carpool.dto.users.UserDtoWeb;
import ru.perminov.carpool.model.Company;
import ru.perminov.carpool.model.User;

import java.util.List;

public interface UserService {
    User create(UserDtoWeb userDto);

    List<UserDtoOut> getAll();

    void update(Long id, UserDtoWeb userDto);

    UserDtoOut getById(Long id);

    UserDtoForMessages getUserDtoByUsername(String name);

    User getByUsername(String username);

    void create(User user);

    User getCurrentUser();

    Company getCompany();
}
