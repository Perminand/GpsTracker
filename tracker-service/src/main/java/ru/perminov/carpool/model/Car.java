package ru.perminov.carpool.model;

import lombok.Data;

import java.util.List;

@Data
public class Car {
    private Long id;

    private String Name;

    private String number;

    private List<Sensors> sensorsList;

    private List<Message> messages;

    public Car(Long id) {
        this.id = id;
    }

}
