package ru.perminov.carpool.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.perminov.carpool.dto.users.UserDto;
import ru.perminov.carpool.dto.users.UserDtoOut;
import ru.perminov.carpool.service.user.UserService;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1/apps/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/welcome")
    @ResponseStatus(HttpStatus.OK)
    public String welcome () {
        return "Welcome to the unprotected page";
    }
}
