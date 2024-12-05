package ru.perminov.carpool.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.perminov.carpool.dto.request.SignInRequest;
import ru.perminov.carpool.dto.request.SignUpRequest;
import ru.perminov.carpool.dto.response.JwtAuthenticationResponse;
import ru.perminov.carpool.security.AuthenticationService;

@Controller
@RequestMapping("/api/v1/apps/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthenticationService authenticationService;

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String auth () {
        return "login";
    }

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/login")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }

//    @Operation(summary = "Авторизация пользователя")
//    @PostMapping("/login")
//    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
//        return authenticationService.signIn(request);
//    }
}