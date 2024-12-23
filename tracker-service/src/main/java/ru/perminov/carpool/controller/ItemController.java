package ru.perminov.carpool.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.perminov.carpool.dto.item.ItemDto;
import ru.perminov.carpool.dto.item.ItemDto2;
import ru.perminov.carpool.service.item.ItemService;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/apps/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto create(@RequestBody ItemDto itemDto) {
        log.info("Пришел POST запрос: {}", itemDto);
        return itemService.create(itemDto);
    }

    @PostMapping("/add2")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto create2(@RequestBody ItemDto2 itemDto) {
        log.info("Пришел POST запрос: {}", itemDto);
        return null;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ItemDto> getAll() throws IOException {
        return itemService.getAll();
    }

}
