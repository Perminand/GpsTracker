package ru.perminov.carpool.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.perminov.carpool.dto.role.RoleDto;
import ru.perminov.carpool.dto.users.UserDto;
import ru.perminov.carpool.dto.users.UserDtoOut;
import ru.perminov.carpool.model.Role;
import ru.perminov.carpool.service.role.RoleService;
import ru.perminov.carpool.service.user.UserService;

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

    @GetMapping("/admin/edit-user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editUserForm(@PathVariable Long id, Model model) {
        UserDtoOut user = userService.getById(id);
        List<RoleDto> roles = roleService.getAll();
        model.addAttribute("user", user);
        model.addAttribute("rolesGroup", roles);
        return "user-edit";
    }

    @PostMapping("/admin/update-user")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateUser(@ModelAttribute UserDto user, Model model) {
        userService.update(user);
        return "redirect:/users-list";
    }

    @RequestMapping("/users")
    @PreAuthorize("hasRole('ADMIN')||hasRole('USER')")
    public String getAllItems() {
        return "user";
    }


}
