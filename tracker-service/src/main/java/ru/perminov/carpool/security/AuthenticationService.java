package ru.perminov.carpool.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.perminov.carpool.dto.request.SignInRequest;
import ru.perminov.carpool.dto.request.SignUpRequest;
import ru.perminov.carpool.dto.response.JwtAuthenticationResponse;
import ru.perminov.carpool.dto.users.UserDto;
import ru.perminov.carpool.model.TokenAccess;
import ru.perminov.carpool.model.User;
import ru.perminov.carpool.repository.TokenAccessRepository;
import ru.perminov.carpool.repository.UserRepository;
import ru.perminov.carpool.service.jwt.JwtService;
import ru.perminov.carpool.service.user.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final TokenAccessRepository tokenAccessRepository;
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserSecurityService userSecurityService;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signUp(SignUpRequest request) {

        UserDto userDto = UserDto.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles("ROLE_USER")
                .build();

        User user = userService.create(userDto);
        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        User user = (User) userSecurityService.loadUserByUsername(request.getUsername());
        TokenAccess tokenAccess = user.getTokenAccess();
        String jwt = jwtService.generateToken(user);
        LocalDate nowTime = LocalDate.now();
        if(tokenAccess == null) {
            tokenAccess = new TokenAccess();
        }
        tokenAccess.setName(jwt);
        tokenAccess.setDataCreated(nowTime);
        tokenAccess.setEndData(nowTime.plusDays(30));
        tokenAccessRepository.save(tokenAccess);
        user.setTokenAccess(tokenAccess);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        return new JwtAuthenticationResponse(jwt);
    }
}