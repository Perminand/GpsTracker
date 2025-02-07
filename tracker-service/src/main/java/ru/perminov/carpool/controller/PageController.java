package ru.perminov.carpool.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.perminov.carpool.dto.message.MessageDto;
import ru.perminov.carpool.dto.role.RoleDto;
import ru.perminov.carpool.dto.users.UserDtoOut;
import ru.perminov.carpool.model.Company;
import ru.perminov.carpool.service.item.ItemService;
import ru.perminov.carpool.service.message.MessageService;
import ru.perminov.carpool.service.role.RoleService;
import ru.perminov.carpool.service.user.UserService;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class PageController {
    private final UserService userService;
    private final RoleService roleService;
    private final MessageService messageService;
    private final ItemService itemService;

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
        model.addAttribute("user", user);
        return "update";
    }

    @GetMapping("/error")
    @PreAuthorize("hasRole('ADMIN')")
    public String getError(Model model) {
        return "error";
    }

    @RequestMapping("/company")
    @PreAuthorize("hasRole('USER')||hasRole('ADMIN')")
    public String getCompany(Model model) {
        Company company = userService.getCompany();
        model.addAttribute("company", company);
        return "company";
    }


    @RequestMapping("/message")
    @PreAuthorize("hasRole('ADMIN')||hasRole('USER')")
    public String getMessages(Model model) {
        try {
            List<MessageDto> messages = messageService.getAll();
            model.addAttribute("messages", messages);
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error(e.getMessage());
            model.addAttribute("error", e);
        } catch (IOException e) {
            log.error(e.getMessage());
            model.addAttribute("error", e);
            return "error";
        }
        return "message";
    }

    @RequestMapping("/items")
    @PreAuthorize("hasRole('ADMIN')||hasRole('USER')")
    public String getItems(Model model) {
        try {
            itemService.getInfo();
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error(e.getMessage());
            model.addAttribute("error", e);
        } catch (IOException e) {
            model.addAttribute("error", e);
        }
        return "items";
    }


}
