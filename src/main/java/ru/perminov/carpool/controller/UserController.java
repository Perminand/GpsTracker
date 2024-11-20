package ru.perminov.carpool.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.perminov.carpool.service.user.UserService;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1/apps")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/welcome")
    @ResponseStatus(HttpStatus.OK)
    public String welcome() {
        return "Welcome to the unprotected page";
    }
}
