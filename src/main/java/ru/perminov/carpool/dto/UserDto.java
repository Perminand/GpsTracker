package ru.perminov.carpool.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.perminov.carpool.model.Role;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    @NotBlank
    @Size(min = 5)
    private String username;

    @NotBlank
    @Email
    private String email;

    @Size(min = 6)
    private String password;
    private List<Role> roles;

}
