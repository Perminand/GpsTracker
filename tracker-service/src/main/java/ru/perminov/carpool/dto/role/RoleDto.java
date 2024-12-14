package ru.perminov.carpool.dto.role;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class RoleDto {
    private Long id;

    @NotBlank
    @Length(min = 3)
    private String name;
    private String description;
}
