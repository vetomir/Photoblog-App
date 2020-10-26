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
    Page<Post> findAll(Pageable pageable);
    Optional<Post> findById(long id);
    void deletePostById(long id);
    Page<Comment> findAllCommentsByPostId(long id, Pageable pageable);
    Page<Post> findAllByContainedQuery(String query, Pageable pageable);
    long getSizeOfAllByContainedQuery(String query);
}
