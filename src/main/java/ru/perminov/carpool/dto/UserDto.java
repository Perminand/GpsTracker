package ru.perminov.carpool.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.perminov.carpool.model.Role;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    @NotBlank
    @Min(3)
    private String username;

    @NotBlank
    @Email
    private String email;

    @Min(6)
    private String password;
    private List<Role> roles;

}
