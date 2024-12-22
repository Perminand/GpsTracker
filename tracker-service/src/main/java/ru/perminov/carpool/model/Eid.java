package ru.perminov.carpool.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Eid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int count;
    private LocalDateTime endTime;
    private String name;


    public Eid(String name) {
        this.name = name;
        this.count = 0;
        this.endTime = LocalDateTime.now().plusMinutes(5);
    }
}
