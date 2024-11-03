package ru.perminov.carpool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.perminov.carpool.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
