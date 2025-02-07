package ru.perminov.carpool.model;

import lombok.Data;

import java.util.List;

@Data
public class Car {
    private Long id;

    private String Name;

    private String model;

    private String year;

    private String color;

    private String engineModel;

    private String enginePower;

    private String primaryFuelType;

    private String vehicleType;

    private String vehicleClass;

    private String fullName;

    private List<Sensor> sensors;

    private String number;

    private List<Sensor> sensorList;

    private List<Message> messages;

    public Car(Long id) {
        this.id = id;
    }

}
