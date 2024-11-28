package ru.perminov.carpool.service.item;

import ru.perminov.carpool.dto.item.ItemDto;

import java.util.List;

public interface ItemService {
    List<ItemDto> getAll();

    ItemDto create(ItemDto itemDto);
}
