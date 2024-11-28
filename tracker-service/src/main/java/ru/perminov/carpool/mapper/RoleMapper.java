package ru.perminov.carpool.mapper;

import ru.perminov.carpool.dto.role.RoleDto;
import ru.perminov.carpool.model.Role;

public class RoleMapper {
    public static Role toEntity(RoleDto roleDto) {
        return new Role(roleDto.getName());
    }
}
