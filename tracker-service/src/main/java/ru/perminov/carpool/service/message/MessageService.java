package ru.perminov.carpool.service.message;

import ru.perminov.carpool.dto.message.MessageDto;

import java.io.IOException;
import java.util.List;

public interface MessageService {
    List<MessageDto> getAll() throws IOException;

    MessageDto create(MessageDto messageDto);
}
