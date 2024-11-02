package ru.perminov.carpool.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.perminov.carpool.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public List<UserDto> getAll(){
        return new ArrayList<>();
    }
}
