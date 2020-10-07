package pl.gregorymartin.b01.core.repository;

import org.springframework.data.domain.Pageable;
import pl.gregorymartin.b01.core.mapping.dto.PostList;
import pl.gregorymartin.b01.core.mapping.dto.PostSingle;
import pl.gregorymartin.b01.core.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Optional<PostSingle> findPostByIdAndMapToDto(long id);
    List<PostList> findAllAndMapToDto(Pageable page);
    Post save(Post post);
    List<Post> findAll();
    Optional<Post> findById(long id);
}
