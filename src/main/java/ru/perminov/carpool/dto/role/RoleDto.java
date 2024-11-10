package ru.perminov.carpool.dto.role;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Builder
@Getter
public class RoleDto {
    private Long id;

    @NotBlank
    @Length(min = 3)
    private String name;
    private String description;
}
