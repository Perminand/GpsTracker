package ru.perminov.carpool.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.perminov.carpool.model.User;
import ru.perminov.carpool.repository.UserRepository;

import java.util.Optional;

@Service
public class UserSecurityService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(username);
        return user.map(UserEntitySecurity::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
