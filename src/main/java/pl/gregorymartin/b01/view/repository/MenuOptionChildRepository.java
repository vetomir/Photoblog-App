package pl.gregorymartin.b01.view.repository;

import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.view.model.MenuOptionChild;

import java.util.List;
import java.util.Optional;

public interface MenuOptionChildRepository {
    MenuOptionChild save(MenuOptionChild post);
    List<MenuOptionChild> findAll();
    Optional<MenuOptionChild> findById(long id);
}
