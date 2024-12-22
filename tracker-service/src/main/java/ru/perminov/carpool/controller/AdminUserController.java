package ru.perminov.carpool.controller;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.perminov.carpool.dto.users.UserDtoWeb;
import ru.perminov.carpool.security.AuthenticationService;
import ru.perminov.carpool.service.user.UserService;

@Slf4j
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminUserController {
    private final AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("/users/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable("id") Long id, @RequestBody UserDtoWeb user) {
        log.info("Пришел запрос на изменение пользователя с ИД: {}, {}", id, user);
        userService.update(id, user);
    }

    @PostMapping("/users/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserDtoWeb userDto) {
        log.info("Пришел POST запрос {}", userDto);
        authenticationService.signUp(userDto);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable @Min(0) Long id) {

    }
}
