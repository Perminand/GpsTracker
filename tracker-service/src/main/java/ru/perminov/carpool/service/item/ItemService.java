package ru.perminov.carpool.service.item;

import ru.perminov.carpool.dto.item.ItemDto;

import java.io.IOException;
import java.util.List;

public interface ItemService {
    List<ItemDto> getAll() throws IOException;

    ItemDto create(ItemDto itemDto);
}
