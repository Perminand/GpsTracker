package ru.perminov.carpool.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.perminov.carpool.model.TokenAccess;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class UserUpdateDto {

    private String username;

    private String realPassword;

    private String email;

    private String tokenAccess;
}
