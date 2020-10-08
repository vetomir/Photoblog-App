package pl.gregorymartin.b01.core.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.gregorymartin.b01.core.mapping.dto.CommentDto;
import pl.gregorymartin.b01.core.mapping.dto.PostList;
import pl.gregorymartin.b01.core.mapping.dto.PostSingle;
import pl.gregorymartin.b01.core.model.Post;

import java.util.List;
import java.util.Optional;

@Repository
public
interface SqlPostRepository extends PostRepository, JpaRepository<Post, Long> {

    @Query("SELECT new pl.gregorymartin.b01.core.mapping.dto.PostList" +
            "(p.description, p.photoUrl, p.createdOn) FROM Post p")
    List<PostList> findAllAndMapToDto(Pageable page);

    @Override
    List<Post> findAll();

    @Override
    Post save(Post post);

    @Override
    Optional<Post> findById(long id);
}
