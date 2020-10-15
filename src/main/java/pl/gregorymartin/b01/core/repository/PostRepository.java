package pl.gregorymartin.b01.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.gregorymartin.b01.core.mapping.model.PostInListReadModel;
import pl.gregorymartin.b01.core.model.Comment;
import pl.gregorymartin.b01.core.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    List<PostInListReadModel> findAllAndMapToDto(Pageable page);
    Post save(Post post);
    List<Post> findAll();
    Optional<Post> findById(long id);
    boolean deleteById(long id);
    Page<Comment> findAllCommentsByPostId(long id, Pageable pageable);
}
