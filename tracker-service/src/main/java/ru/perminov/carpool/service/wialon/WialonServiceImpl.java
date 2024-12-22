package ru.perminov.carpool.service.wialon;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.perminov.carpool.client.ClientWialon;
import ru.perminov.carpool.exceptions.errors.EntityNotFoundException;
import ru.perminov.carpool.model.TokenWialon;
import ru.perminov.carpool.model.User;
import ru.perminov.carpool.repository.TokenWealonRepository;
import ru.perminov.carpool.service.user.UserService;

import java.net.URISyntaxException;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class WialonServiceImpl implements WialonService {
    private final ClientWialon clientWialon;
    private final TokenWealonRepository tokenWealonRepository;
    private final UserService userService;


    @Override
    public void getTokenWialon(User user) {
        String wialonJwt;
        TokenWialon tokenWialon = new TokenWialon();
        try {
            wialonJwt = clientWialon.getToken(user.getUsername(), user.getRealPassword());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EntityNotFoundException(e.getMessage());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        tokenWialon.setName(wialonJwt);
        tokenWialon.setDataCreated(LocalDate.now());
        tokenWialon.setEndData(LocalDate.now().plusDays(30));
        createWealon(tokenWialon);
        user.setTokenWialon(tokenWialon);
        userService.create(user);
    }

    @Override
    public TokenWialon createWealon(TokenWialon tokenWialon) {
        return tokenWealonRepository.save(tokenWialon);
    }
}
