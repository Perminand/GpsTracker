package ru.perminov.carpool.service.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.perminov.carpool.client.ClientWialon;
import ru.perminov.carpool.dto.item.ItemDto;
import ru.perminov.carpool.mapper.ItemMapper;
import ru.perminov.carpool.model.*;
import ru.perminov.carpool.repository.ItemRepository;
import ru.perminov.carpool.service.user.UserService;
import ru.perminov.carpool.service.wialon.WialonService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserService userService;
    private final WialonService wialonService;
    private final ClientWialon clientWialon;

    @Override
    public Model getAll(Model model) throws IOException {
        List<Item> items = new ArrayList<>();
        User user = userService.getCurrentUser();

        for (Role r : user.getRoles()) {
            if (r.getName().equals("ROLE_USER")) {
                TokenWialon tokenWialon = user.getTokenWialon();
                if (tokenWialon == null || tokenWialon.getEndData().isBefore(LocalDate.now())) {
                    wialonService.getTokenWialon(user);
                    tokenWialon = user.getTokenWialon();
                }

                List<Car> cars = clientWialon.getCars(tokenWialon, user);
                for (Car c : cars) {
                    items = clientWialon.getItem(tokenWialon, user, c);
//                        clientWialon.getSensors(tokenWialon, user, c);
                }

            }
            break;
        }
        model.addAttribute("items", items);
        return model;
    }

    @Override
    public ItemDto create(ItemDto itemDto) {
        Item item = ItemMapper.toEntity(itemDto);
        itemRepository.save(item);
        return ItemMapper.toDto(item);
    }
}
