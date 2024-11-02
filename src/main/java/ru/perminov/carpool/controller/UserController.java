package ru.perminov.carpool.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.perminov.carpool.dto.UserDto;
import ru.perminov.carpool.dto.UserDtoOut;
import ru.perminov.carpool.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserDtoOut create (@RequestBody UserDto userDto) {
        log.info("Пришел POST запрос {}", userDto);
        return userService.create(userDto);
    }

    @GetMapping
    public List<UserDto> getAll(){
        log.info("Пришел GET запрос");
        return new ArrayList<>();
    }
}
