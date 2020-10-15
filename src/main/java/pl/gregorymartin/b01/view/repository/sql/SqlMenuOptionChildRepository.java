package pl.gregorymartin.b01.view.repository.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gregorymartin.b01.view.model.MenuOption;
import pl.gregorymartin.b01.view.model.MenuOptionChild;
import pl.gregorymartin.b01.view.repository.MenuOptionChildRepository;
import pl.gregorymartin.b01.view.repository.MenuOptionRepository;
@Repository
interface SqlMenuOptionChildRepository extends MenuOptionChildRepository, JpaRepository<MenuOptionChild, Long> {
}
