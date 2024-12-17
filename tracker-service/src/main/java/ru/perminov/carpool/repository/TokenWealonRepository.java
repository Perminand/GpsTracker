package ru.perminov.carpool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.perminov.carpool.model.TokenWialon;

public interface TokenWealonRepository extends JpaRepository<TokenWialon, Long> {
}
