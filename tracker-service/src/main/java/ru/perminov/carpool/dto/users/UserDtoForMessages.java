package ru.perminov.carpool.dto.users;

import lombok.Builder;
import lombok.Data;
import ru.perminov.carpool.model.Role;

import java.util.List;

@Data
@Builder
public class UserDtoForMessages {
    private String username;
    private List<Role> roles;
}
