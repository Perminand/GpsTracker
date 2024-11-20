package ru.perminov.carpool.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.perminov.carpool.dto.item.ItemDto;
import ru.perminov.carpool.service.item.ItemService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/apps/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto create(@RequestBody ItemDto itemDto) {
        log.info("Пришел POST запрос: {}", itemDto);
        return itemService.create(itemDto);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getAll() {
        log.info("Пришел Get запрос");
        StringBuilder sb = new StringBuilder();
        List<ItemDto> list = itemService.getAll();
        sb.append("<!DOCTYPE html> <html lang=\"ru\"> <head> <meta charset=\"UTF-8\"> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> <title>Заголовок страницы</title> <style> table { border-collapse: collapse; width: 100%; } th, td { padding: 8px; text-align: left; border-bottom: 1px solid #ddd; } th { background-color: #4CAF50; color: white; } </style> </head> <body> <h2>Данные</h2> <table> <tr> <th>ID</th> <th>Дата создания</th> <th>Сообщение</th> </tr>");
        for (ItemDto dto: list) {
            sb.append("<tr><td>" +  dto.getId() + "</td> <td>" + dto.getCreated() + "</td> <td>" + dto.getMessage() + "</td></tr>");
        }
        sb.append(" </table> </body> </html>");
        return sb.toString();
    }
}
