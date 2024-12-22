package ru.perminov.carpool.model;

import lombok.Data;

import java.util.List;

@Data
public class Car {
    private Long id;

    private List<Sensors> sensorsList;

    private List<Item> items;

    public Car(Long id) {
        this.id = id;
    }

}
