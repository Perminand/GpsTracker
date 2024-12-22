package ru.perminov.carpool.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.perminov.carpool.service.jwt.JwtService;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserSecurityService userSecurityService;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromCookie(request);
        UserDetails user;
        if (isTokenValid(token)) {
            user = jwtService.isTokenValid(token, userSecurityService);
            JwtAuthentication jwtAuthentication = new JwtAuthentication(user.getUsername(), user.getAuthorities(), request);
            SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private boolean isTokenValid(String token) {
        try {
            jwtService.isTokenValid(token, userSecurityService);
        } catch (Exception e) {
            return false;// Здесь можно реализовать более строгую проверку валидности токена
        }
        return userSecurityService.existTokenByName(token); // Пример простой проверки
    }

    private String extractUserName(String token) {
        // Здесь можно извлечь имя пользователя из токена
        return "user_name_from_token";
    }

    private Collection<? extends GrantedAuthority> extractAuthorities(String token) {
        // Здесь можно извлечь роли пользователя из токена
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
}



