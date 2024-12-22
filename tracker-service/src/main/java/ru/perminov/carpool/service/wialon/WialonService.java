package ru.perminov.carpool.service.wialon;

import ru.perminov.carpool.model.TokenWialon;
import ru.perminov.carpool.model.User;

public interface WialonService {
    void getTokenWialon(User user);

    TokenWialon createWealon(TokenWialon tokenWialon);
}
