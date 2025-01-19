package ru.perminov.carpool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.perminov.carpool.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
