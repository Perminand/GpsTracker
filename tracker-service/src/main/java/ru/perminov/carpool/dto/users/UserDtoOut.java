package ru.perminov.carpool.dto.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.perminov.carpool.model.Role;
import ru.perminov.carpool.model.TokenAccess;
import ru.perminov.carpool.model.TokenWialon;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoOut {
    private Long id;
    private String username;
    private String realPassword;
    private String email;
    private List<Role> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private TokenAccess tokenAccess;

}
