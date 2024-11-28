package ru.perminov.carpool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.perminov.carpool.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);
}
