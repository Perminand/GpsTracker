package ru.perminov.carpool.dto.item;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ItemDto {

    private Long id;

    private LocalDateTime created;

    @NotBlank
    private String message;
}
