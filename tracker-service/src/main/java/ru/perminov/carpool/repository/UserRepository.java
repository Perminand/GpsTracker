package ru.perminov.carpool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.perminov.carpool.model.Company;
import ru.perminov.carpool.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);

    @Query(value = "select c from Company c where id = (select id from User where username = :currentUser)")
    Company getCompanyByUsername(User currentUser);


}
