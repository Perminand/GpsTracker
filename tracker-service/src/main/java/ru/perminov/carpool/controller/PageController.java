package ru.perminov.carpool.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.perminov.carpool.dto.role.RoleDto;
import ru.perminov.carpool.dto.users.UserDto;
import ru.perminov.carpool.dto.users.UserDtoForItems;
import ru.perminov.carpool.dto.users.UserDtoOut;
import ru.perminov.carpool.model.Role;
import ru.perminov.carpool.model.User;
import ru.perminov.carpool.service.role.RoleService;
import ru.perminov.carpool.service.user.UserService;

import java.net.URI;
import java.util.Collection;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class PageController {
    private final UserService userService;
    private final RoleService roleService;

    @RequestMapping("api/v1/apps/auth/login")
    public String getIndex() {
        return "login";
    }

    @RequestMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public void getAll() {

    }

    @GetMapping("/admin/users-list")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminPage(Model model) {
        List<UserDtoOut> users = userService.getAll();
        List<RoleDto> roles = roleService.getAll();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "users-list";
    }

    @GetMapping("/admin/create")
    @PreAuthorize("hasRole('ADMIN')")
    public String createUser(Model model) {
        List<RoleDto> roles = roleService.getAll();
        model.addAttribute("roles", roles);
        return "create";
    }


    @GetMapping("/admin/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editUserForm(@PathVariable Long id, Model model) {
        UserDtoOut user = userService.getById(id);
        List<RoleDto> roles = roleService.getAll();
        model.addAttribute("user", user);
        return "update";
    }


    @RequestMapping("/items")
    @PreAuthorize("hasRole('ADMIN')||hasRole('USER')")
    public String getAllItems(Model model) {
         return "items";
    }


}
