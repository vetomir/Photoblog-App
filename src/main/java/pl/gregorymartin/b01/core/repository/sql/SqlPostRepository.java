package pl.gregorymartin.b01.core.repository.sql;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.gregorymartin.b01.core.mapping.model.PostInListReadModel;
import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.core.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Repository
public
interface SqlPostRepository extends PostRepository, JpaRepository<Post, Long> {

    @Query("SELECT new pl.gregorymartin.b01.core.mapping.model.PostInListReadModel" +
            "(p.description, " +
            "p.photoUrl, " +
            "p.createdOn, " +
            "p.numberOfComments, " +
            "p.numberOfLikes, " +
            "true) " +
            "FROM Post p")
    List<PostInListReadModel> findAllAndMapToDto(Pageable page);

    @Override
    List<Post> findAll();

    @Override
    Post save(Post post);

    @Override
    Optional<Post> findById(long id);
}
