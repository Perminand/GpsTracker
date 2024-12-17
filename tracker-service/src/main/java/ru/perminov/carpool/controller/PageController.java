package ru.perminov.carpool.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.perminov.carpool.client.ClientWialon;
import ru.perminov.carpool.dto.role.RoleDto;
import ru.perminov.carpool.dto.users.UserDtoOut;
import ru.perminov.carpool.model.Role;
import ru.perminov.carpool.model.User;
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
    private final ClientWialon client;

    @RequestMapping("api/v1/apps/auth/login")
    public String getIndex() {
        return "login";
    }

    @RequestMapping("/")
    @PreAuthorize("hasRole('ADMIN')||hasRole('USER')")
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

    @GetMapping("/error")
    @PreAuthorize("hasRole('ADMIN')")
    public String getError(Model model) {
        return "error";
    }


    @RequestMapping("/items")
    @PreAuthorize("hasRole('ADMIN')||hasRole('USER')")
    public String getAllItems(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            String wialonJwt;
            User user = userService.getByUsername(authentication.getName());
            for (Role r : user.getRoles()) {
                if (r.getName().equals("ROLE_USER")) ;
                {
                    try {
                        userService.getTokenWialon(user);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        model.addAttribute("error", e);
                        if (user.getRoles().get(0).equals("ROLE_ADMIN")) {

                        }
                        return "error";
                    }
                }
                break;
            }
            if (user.getRoles().get(0).equals("ROLE_ADMIN")) {

            }

        }

        return "items";
    }


}
