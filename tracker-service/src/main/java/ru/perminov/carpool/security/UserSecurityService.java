package ru.perminov.carpool.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.perminov.carpool.model.Role;
import ru.perminov.carpool.model.User;
import ru.perminov.carpool.repository.RoleRepository;
import ru.perminov.carpool.repository.TokenAccessRepository;
import ru.perminov.carpool.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {
    private final UserRepository repository;
    private final TokenAccessRepository tokenAccessRepository;
    private final RoleRepository roleRepository;

    @Value("${admin.username}")
    String adminName;

    @Value("${admin.password}")
    String adminPassword;

    @Value("${admin.email}")
    String adminEmail;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(repository.existsByUsername(username)) {
            return repository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username + " not found"));
        } else  {
            return getAdmin(username);

        }
    }

    private User getAdmin(String userName) {
        BCryptPasswordEncoder coder = new BCryptPasswordEncoder();
        if (userName.equals(adminName)) {
            Role role = roleRepository.findByName("ROLE_ADMIN").get();
            User user = new User();
            user = new User();
            user.setUsername(adminName);
            user.setEmail(adminEmail);
            user.setPassword(coder.encode(adminPassword));
            user.setRealPassword(adminPassword);
            user.setRoles(List.of(role));
            return user;
        }
        throw new UsernameNotFoundException(userName + " not found");
    }

    public boolean existTokenByName(String token) {
        return tokenAccessRepository.existsByName(token);
    }
}
