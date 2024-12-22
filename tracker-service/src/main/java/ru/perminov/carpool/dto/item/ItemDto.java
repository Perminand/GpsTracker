package ru.perminov.carpool.dto.item;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDto {

    private Long id;

    private String date;
    private String Car;
    private int t;
    private int p;
    private int s;
    private String address;

}
