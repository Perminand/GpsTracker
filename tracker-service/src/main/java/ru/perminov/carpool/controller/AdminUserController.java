package ru.perminov.carpool.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.perminov.carpool.dto.users.UserUpdateDto;
import ru.perminov.carpool.service.user.UserService;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @PostMapping("/update-user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable("id") Long id, @RequestBody UserUpdateDto user) {
        log.info("Пришел запрос на изменение пользователя с ИД: {}, {}", id, user);
        userService.update(id, user);

    }
}
