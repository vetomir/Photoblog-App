package pl.gregorymartin.b01.core.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.gregorymartin.b01.core.mapping.dto.PostList;
import pl.gregorymartin.b01.core.mapping.dto.TagDto;
import pl.gregorymartin.b01.core.model.Tag;

import java.util.List;

@Repository
public interface SqlTagRepository extends TagRepository, JpaRepository<Tag, Long> {

    @Query("SELECT new pl.gregorymartin.b01.core.mapping.dto.TagDto" +
            "(t.title) FROM Tag t")
    List<TagDto> findAllAndMapToDto(Pageable page);
}
