package pl.gregorymartin.b01.security.repository.sql;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.security.model.User;
import pl.gregorymartin.b01.security.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Repository
interface SqlUserRepository extends UserRepository, JpaRepository<User, Long> {
    List<User> findAll();

    User findAllByUsername(String username);
    User findByName(String name);
    Optional<User> findById(long id);



    @Query("Select p.posts From User p where p.name = ?1")
    List<Post> findAllPostsByUser_Name(String name, Pageable page);

}
