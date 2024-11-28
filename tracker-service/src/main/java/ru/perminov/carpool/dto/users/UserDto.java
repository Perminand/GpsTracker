package ru.perminov.carpool.dto.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    @NotBlank
    @Length(min = 5)
    private String username;

    @NotBlank
    @Email
    private String email;

    @Length(min = 6)
    private String password;
    private String roles;

}
