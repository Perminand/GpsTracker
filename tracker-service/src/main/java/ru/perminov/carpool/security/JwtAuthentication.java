package ru.perminov.carpool.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.net.http.HttpRequest;
import java.util.Collection;

public class JwtAuthentication implements Authentication {

    private String username;
    private Collection<? extends GrantedAuthority> authorities;
    private Object credentials;
    private boolean authenticated;
    private Object principal;
    private WebAuthenticationDetails details;
    private HttpServletRequest request;

    public JwtAuthentication(String username, Collection<? extends GrantedAuthority> authorities, HttpServletRequest request) {
        this.username = username;
        this.authorities = authorities;
        this.authenticated = true;
        this.principal = username;
        this.credentials = null; // JWT token is not used for credentials
        this.details = new WebAuthenticationDetails(request);
        this.request = request; // Здесь request передается в конструктор
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) {
        this.authenticated = isAuthenticated;
    }

    public WebAuthenticationDetails getDetails() {
        return details;
    }

    public void setDetails(WebAuthenticationDetails details) {
        this.details = details;
    }

    /**
     * Returns the name of this {@code Principal}.
     *
     * @return the name of this {@code Principal}.
     */
    @Override
    public String getName() {
        return username;
    }

    public static UsernamePasswordAuthenticationToken createAuthenticationToken(String username, Collection<? extends GrantedAuthority> authorities, HttpServletRequest request) {
        return new UsernamePasswordAuthenticationToken(
                new JwtAuthentication(username, authorities, request),
                null,
                authorities
        );
    }
}