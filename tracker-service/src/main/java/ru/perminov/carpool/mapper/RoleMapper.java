package ru.perminov.carpool.mapper;

import ru.perminov.carpool.dto.role.RoleDto;
import ru.perminov.carpool.model.Role;

import java.util.List;

public class RoleMapper {
    public static Role toEntity(RoleDto roleDto) {
        return new Role(roleDto.getName());
    }

    public static RoleDto toDto(Role role) {
        return RoleDto.builder().id(role.getId()).name(role.getName()).description(role.getDescription()).build();
    }
}
