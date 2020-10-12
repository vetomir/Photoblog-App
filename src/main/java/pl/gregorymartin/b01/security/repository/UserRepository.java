package pl.gregorymartin.b01.security.repository;

import org.springframework.data.domain.Pageable;
import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.security.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();
    List<User> findAll(Pageable pageable);

    User findAllByUsername(String username);
    User findByName(String name);
    Optional<User> findById(long id);
    User save(User user);


    List<Post> findAllPostsByUser_Name(String name, Pageable page);
}
