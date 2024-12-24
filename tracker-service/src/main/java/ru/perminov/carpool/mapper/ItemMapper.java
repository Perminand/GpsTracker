package ru.perminov.carpool.mapper;

import ru.perminov.carpool.dto.item.ItemDto;
import ru.perminov.carpool.model.Item;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ItemMapper {
    public static Item toEntity(ItemDto dto) {
        Item item = new Item();
        item.setCreated(ZonedDateTime.parse(dto.getCreated(), DateTimeFormatter.ISO_DATE_TIME));
        item.setCar(dto.getCar());
        item.setS(dto.getS());
        item.setP(dto.getP());
        item.setAddress(dto.getAddress());
        return item;
    }

    public static ItemDto toDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .created(item.getCreated().format(DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy")))
                .Car(item.getCar())
                .p(item.getP())
                .s(item.getS())
                .address(item.getAddress())
                .message(item.getMessage())
                .build();
    }
}
