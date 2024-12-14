package ru.perminov.carpool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.perminov.carpool.model.TokenAccess;

public interface TokenAccessRepository extends JpaRepository<TokenAccess, Long> {
}
