package ru.perminov.carpool.mapper;

import ru.perminov.carpool.dto.message.MessageDto;
import ru.perminov.carpool.model.Message;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class MessageMapper {
    public static Message toEntity(MessageDto dto) {
        Message message = new Message();
        message.setCreated(ZonedDateTime.parse(dto.getCreated(), DateTimeFormatter.ISO_DATE_TIME));
        message.setCar(dto.getCar());
        message.setS(dto.getS());
        message.setP(dto.getP());
        message.setAddress(dto.getAddress());
        return message;
    }

    public static MessageDto toDto(Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .created(message.getCreated().format(DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy")))
                .Car(message.getCar())
                .p(message.getP())
                .s(message.getS())
                .address(message.getAddress())
                .message(message.getMessage())
                .build();
    }
}
