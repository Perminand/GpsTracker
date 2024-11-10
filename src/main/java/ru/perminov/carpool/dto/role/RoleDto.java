package ru.perminov.carpool.dto.role;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RoleDto {
    private Long id;
    @NotBlank
    @Size(min = 3)
    private String name;
    private String description;
}
