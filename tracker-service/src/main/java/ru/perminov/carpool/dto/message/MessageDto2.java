package ru.perminov.carpool.dto.message;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDto2 {

    private Long id;

    private String POS_TIME;
    private String UNIT;
    private String SENSOR_VALUE;
    private String SPEED;
    private String LOCATION;

}
