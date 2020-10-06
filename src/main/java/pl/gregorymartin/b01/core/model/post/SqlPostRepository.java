package pl.gregorymartin.b01.core.model.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.gregorymartin.b01.core.mapping.dto.PostDto;

import java.util.List;
import java.util.Optional;

@Repository
public
interface SqlPostRepository extends /*PostRepository,*/ JpaRepository<Post, Long> {

    @Query("SELECT new pl.gregorymartin.b01.core.mapping.dto.PostDto" +
            "(p.description, p.photoUrl, p.createdOn) FROM Post p WHERE p.id = :id")
    Optional<PostDto> findPostByIdAndMapToDto(long id);

    @Query("SELECT new pl.gregorymartin.b01.core.mapping.dto.PostDto" +
            "(p.description, p.photoUrl, p.createdOn) FROM Post p")
    List<PostDto> findAllAndMapToDto();
}
