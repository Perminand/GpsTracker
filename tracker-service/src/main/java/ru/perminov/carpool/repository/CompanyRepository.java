package ru.perminov.carpool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.perminov.carpool.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
