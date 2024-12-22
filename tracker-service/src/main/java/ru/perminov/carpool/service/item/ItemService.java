package ru.perminov.carpool.service.item;

import org.springframework.ui.Model;
import ru.perminov.carpool.dto.item.ItemDto;

import java.io.IOException;

public interface ItemService {
    Model getAll(Model model) throws IOException;

    ItemDto create(ItemDto itemDto);
}
