package ru.perminov.carpool.mapper;

import ru.perminov.carpool.dto.item.ItemDto;
import ru.perminov.carpool.model.Item;

import java.time.LocalDateTime;

public class ItemMapper {
    public static Item toEntity(ItemDto dto) {
        Item item = new Item();
        item.setMessage(dto.getMessage());
        item.setCreated(LocalDateTime.now());
        return item;
    }

    public static ItemDto toDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .message(item.getMessage())
                .created(item.getCreated())
                .build();
    }
}
