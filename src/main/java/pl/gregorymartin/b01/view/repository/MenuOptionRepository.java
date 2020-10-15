package pl.gregorymartin.b01.view.repository;

import pl.gregorymartin.b01.view.model.MenuOption;
import pl.gregorymartin.b01.view.model.MenuOptionChild;

import java.util.List;
import java.util.Optional;

public interface MenuOptionRepository {
    MenuOption save(MenuOption post);
    List<MenuOption> findAll();
    Optional<MenuOption> findById(long id);
}
