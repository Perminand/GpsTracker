package ru.perminov.carpool.mapper;

import ru.perminov.carpool.dto.item.ItemDto;
import ru.perminov.carpool.model.Item;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ItemMapper {
    public static Item toEntity(ItemDto dto) {
        Item item = new Item();
        item.setDateTime(ZonedDateTime.parse(dto.getDate(), DateTimeFormatter.ISO_DATE_TIME));
        item.setCar(dto.getCar());
        item.setS(dto.getS());
        item.setP(dto.getP());
        item.setAddress(dto.getAddress());
        return item;
    }

    public static ItemDto toDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .date(item.getDateTime().toString())
                .Car(item.getCar())
                .p(item.getP())
                .s(item.getS())
                .address(item.getAddress())
                .build();
    }
}
