package pl.gregorymartin.b01.core.repository.sql;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.gregorymartin.b01.core.mapping.model.TagReadModel;
import pl.gregorymartin.b01.core.model.Tag;
import pl.gregorymartin.b01.core.repository.TagRepository;

import java.util.List;

@Repository
public interface SqlTagRepository extends TagRepository, JpaRepository<Tag, Long> {

    @Query("SELECT new pl.gregorymartin.b01.core.mapping.model.TagReadModel" +
            "(t.title) FROM Tag t")
    List<TagReadModel> findAllAndMapToDto(Pageable page);
}
