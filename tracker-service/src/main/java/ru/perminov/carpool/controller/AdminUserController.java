package ru.perminov.carpool.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.perminov.carpool.dto.role.RoleDto;
import ru.perminov.carpool.dto.users.UserDtoOut;
import ru.perminov.carpool.dto.users.UserUpdateDto;
import ru.perminov.carpool.model.User;
import ru.perminov.carpool.service.user.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @PostMapping("/update-user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updateUser(@PathVariable("id") Long id, @RequestBody UserUpdateDto user) {
        UserDtoOut updatedUser = userService.update(id, user);
        return ResponseEntity.ok("Пользователь успешно обновлен");
    }
}
