package ru.perminov.carpool.service.role;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import ru.perminov.carpool.dto.role.RoleDto;
import ru.perminov.carpool.dto.users.UserDtoOut;
import ru.perminov.carpool.model.Role;

import java.util.List;

public interface RoleService {
    Role create(RoleDto roleDto);

    List<Role> getAll();

    Role getById(Long id);
}
