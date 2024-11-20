package ru.perminov.carpool.service.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.perminov.carpool.dto.item.ItemDto;
import ru.perminov.carpool.mapper.ItemMapper;
import ru.perminov.carpool.model.Item;
import ru.perminov.carpool.repository.ItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    public List<ItemDto> getAll() {
        return itemRepository.findAll().stream().map(ItemMapper::toDto).toList();
    }

    @Override
    public ItemDto create(ItemDto itemDto) {
        Item item = ItemMapper.toEntity(itemDto);
        itemRepository.save(item);
        return ItemMapper.toDto(item);
    }
}
