package ru.perminov.carpool.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class PageController {

    @GetMapping("/api/v1/apps/auth/login")
    @ResponseStatus(HttpStatus.OK)
    public String getIndex() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"ru\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>JWT Authentication</title>\n" +
                "    <link rel=\"stylesheet\" href=\"/css/styles.css\">\n" +
                "    <script src=\"/js/auth.js\"></script>\n" +
                "</head>\n" +
                "<body>\n" +
                "<header>\n" +
                "    <h1>GPS Tracker</h1>\n" +
                "</header>\n" +
                "<main>\n" +
                "    <div class=\"container\">\n" +
                "        <form action=\"/api/v1/apps/auth/login\" method=\"post\" id=\"form\">\n" +
                "            <label for=\"username\">Username:</label>\n" +
                "            <input type=\"text\" id=\"username\" name=\"username\" required><br>\n" +
                "            <label for=\"password\">Password:</label>\n" +
                "            <input type=\"password\" id=\"password\" name=\"password\" required><br>\n" +
                "            <button type=\"submit\">Login</button>\n" +
                "        </form>\n" +
                "    </div>\n" +
                "</main>\n" +
                "<footer><p>&copy; 2024 Perminov production</p>" +
                "</footer>\n" +
                "</body>\n" +
                "</html>";
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void getAll() {

    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public String adminPage() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"ru\">\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                " <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                " <title>Пользователи</title>\n" +
                " <style>\n" +
                " table {\n" +
                " width: 100%;\n" +
                " border-collapse: collapse;\n" +
                " }\n" +
                " th, td {\n" +
                " border: 1px solid #ddd;\n" +
                " padding: 8px;\n" +
                " text-align: left;\n" +
                " }\n" +
                " th {\n" +
                " background-color: #f2f2f2;\n" +
                " }\n" +
                " </style>\n" +
                " <script src=\"users.js\"></script>\n" +
                "</head>\n" +
                "<body>\n" +
                " <table id=\"user-table\">\n" +
                " <thead>\n" +
                " <tr>\n" +
                " <th>ID</th>\n" +
                " <th>Имя пользователя</th>\n" +
                " <th>Email</th>\n" +
                " <th>Роль</th>\n" +
                " <th>Действия</th>\n" +
                " </tr>\n" +
                " </thead>\n" +
                " <tbody>\n" +
                " <!-- Здесь будут данные пользователей -->\n" +
                " </tbody>\n" +
                " </table>\n" +
                "\n" +
                " <div class=\"filter-container\">\n" +
                " <input type=\"text\" id=\"username-filter\" placeholder=\"Поиск по имени пользователя\">\n" +
                " <input type=\"text\" id=\"email-filter\" placeholder=\"Поиск по email\">\n" +
                " </div>\n" +
                "</body>\n" +
                "</html>";
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('USER')||hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public String getAllItems() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"ru\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>GPS Tracker</title>\n" +
                "    <link rel=\"stylesheet\" href=\"/css/styles.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <h1>Таблица с данными</h1>\n" +
                "\n" +
                "        <table id=\"item-table\">\n" +
                "            <thead>\n" +
                "                <tr>\n" +
                "                    <th>ID</th>\n" +
                "                    <th>Дата создания</th>\n" +
                "                    <th>Сообщение</th>\n" +
                "                </tr>\n" +
                "            </thead>\n" +
                "            <tbody></tbody>\n" +
                "        </table>\n" +
                "\n" +
                "        <input type=\"text\" id=\"message-filter\" placeholder=\"Введите сообщение для фильтрации\">\n" +
                "\n" +
                "<button id=\"logout-button\">Выйти</button>" +
                "        <script src=\"/js/table.js\"></script>\n" +
                "<script src=\"/js/logout.js\"></script>" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
    }


}
