package ru.perminov.carpool.service.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.perminov.carpool.client.ClientWialon;
import ru.perminov.carpool.dto.message.MessageDto;
import ru.perminov.carpool.mapper.MessageMapper;
import ru.perminov.carpool.model.*;
import ru.perminov.carpool.repository.MessageRepository;
import ru.perminov.carpool.service.user.UserService;
import ru.perminov.carpool.service.wialon.WialonService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserService userService;
    private final WialonService wialonService;
    private final ClientWialon clientWialon;

    @Override
    public List<MessageDto> getAll() {
        List<Car> cars;
        List<Message> messagesAll = new ArrayList<>();
        User user = userService.getCurrentUser();

        for (Role r : user.getRoles()) {
            if (r.getName().equals("ROLE_USER")) {
                TokenWialon tokenWialon = user.getTokenWialon();
                if (tokenWialon == null || tokenWialon.getEndData().isBefore(LocalDate.now())) {
                    wialonService.getTokenWialon(user);
                    tokenWialon = user.getTokenWialon();
                }

                try {
                    cars = clientWialon.getCars(tokenWialon, user);
                    for (Car c : cars) {
                        List<Message> messages;
                        messages = clientWialon.getMessage(tokenWialon, user, c);
                        messagesAll.addAll(messages);
                        c.setMessages(messages);
                        }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            break;
        }
        return messagesAll.stream().filter(message -> message.getName().equals("Температура 10-90")).sorted((message1, message2) -> {
            ZonedDateTime dt1 = message1.getCreated();
            ZonedDateTime dt2 = message2.getCreated();
            return dt2.compareTo(dt1); // Сортировка по убыванию даты
        }).map(MessageMapper::toDto).toList();
    }

    @Override
    public MessageDto create(MessageDto messageDto) {
        Message message = MessageMapper.toEntity(messageDto);
        messageRepository.save(message);
        return MessageMapper.toDto(message);
    }
}
