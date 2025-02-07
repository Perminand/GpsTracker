package ru.perminov.carpool.service.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.perminov.carpool.client.ClientWialon;
import ru.perminov.carpool.model.Car;
import ru.perminov.carpool.model.Role;
import ru.perminov.carpool.model.TokenWialon;
import ru.perminov.carpool.model.User;
import ru.perminov.carpool.service.user.UserService;
import ru.perminov.carpool.service.wialon.WialonService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ClientWialon clientWialon;
    private final UserService userService;
    private final WialonService wialonService;

    @Override
    public void getInfo() throws IOException {
        List<Car> cars;
        User user = userService.getCurrentUser();
        for (Role r : user.getRoles()) {
            if (r.getName().equals("ROLE_USER")) {
                TokenWialon tokenWialon = user.getTokenWialon();
                if (tokenWialon == null || tokenWialon.getEndData().isBefore(LocalDate.now())) {
                    wialonService.getTokenWialon(user);
                    tokenWialon = user.getTokenWialon();
                }
                cars = clientWialon.getCars(tokenWialon, user);

            } else break;

        }
    }
}
