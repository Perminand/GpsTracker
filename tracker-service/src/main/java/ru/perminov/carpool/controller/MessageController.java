package ru.perminov.carpool.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.perminov.carpool.dto.message.MessageDto;
import ru.perminov.carpool.dto.message.MessageDto2;
import ru.perminov.carpool.service.message.MessageService;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/apps/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDto create(@RequestBody MessageDto messageDto) {
        log.info("Пришел POST запрос: {}", messageDto);
        return messageService.create(messageDto);
    }

    @PostMapping("/add2")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDto create2(@RequestBody MessageDto2 messageDto) {
        log.info("Пришел POST запрос: {}", messageDto);
        return null;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MessageDto> getAll() throws IOException {
        return messageService.getAll();
    }

}
