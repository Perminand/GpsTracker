package ru.perminov.carpool.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.perminov.carpool.dto.role.RoleDto;
import ru.perminov.carpool.dto.users.UserDto;
import ru.perminov.carpool.dto.users.UserDtoOut;
import ru.perminov.carpool.model.Role;
import ru.perminov.carpool.model.User;
import ru.perminov.carpool.service.role.RoleService;
import ru.perminov.carpool.service.user.UserService;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1/apps/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@Valid @RequestBody UserDto userDto) {
        log.info("Пришел POST запрос {}", userDto);
        userService.create(userDto);
    }

//    @PatchMapping("/users/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public String updateUser(@ModelAttribute UserDto userDto, Model model) {
//        log.info("Пришел PATCH запрос на изменение данных");
//        userService.update(userDto);
//        return "redirect:/users-list";
//    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDtoOut> getAll() {
        log.info("Пришел GET запрос на получения списка пользователей");
        return userService.getAll();
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable @Min(0) Long id) {

    }

    @PostMapping("/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public Role createRole(@RequestBody @Valid RoleDto roleDto) {
        log.info("");
        return roleService.create(roleDto);
    }

    @GetMapping("/roles")
    @ResponseStatus(HttpStatus.OK)
    public List<Role> getAllRoles() {
        log.info("");
        return roleService.getAll();
    }

    @GetMapping("/roles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Role getAllRoles(@Min(1) @PathVariable Long id) {
        log.info("");
        return roleService.getById(id);
    }

    @PatchMapping("/roles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Role updateRole(@RequestBody @Valid Role role,
                           @PathVariable @Min(1) Long id) {
        log.info("Пришел PATCH запрос на изменение данных");
        return roleService.update(role, id);
    }

    @DeleteMapping("/roles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRole(@PathVariable @Min(0) Long id) {
        roleService.deleteById(id);
    }
}
