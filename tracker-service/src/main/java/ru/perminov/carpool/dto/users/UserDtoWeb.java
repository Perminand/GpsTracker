package ru.perminov.carpool.dto.users;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.perminov.carpool.markers.Create;
import ru.perminov.carpool.model.Role;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class UserDtoWeb {

    @NotNull(groups = Create.class)
    private String username;

    @NotNull(groups = Create.class)
    private String realPassword;

    @NotNull(groups = Create.class)
    private String email;

    private String tokenAccess;

    private String roles;
}
