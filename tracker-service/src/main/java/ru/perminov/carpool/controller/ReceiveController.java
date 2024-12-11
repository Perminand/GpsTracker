package ru.perminov.carpool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/receive")
public class ReceiveController {

    @PostMapping(value = "/form", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> receiveFormData(@RequestBody Map<String, String> data) {
        // Обработка полученных данных
        // Здесь можно выполнить аутентификацию и получить redirect_url
        String redirectUrl = "http://62.109.21.174:8081/wialon"; // замените на ваш URL
        System.out.println(redirectUrl);
        return ResponseEntity.status(HttpStatus.OK).body(redirectUrl);
    }
}