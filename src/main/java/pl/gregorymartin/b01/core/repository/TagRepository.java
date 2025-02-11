package pl.gregorymartin.b01.core.repository;

import org.springframework.data.domain.Pageable;
import pl.gregorymartin.b01.core.mapping.model.TagReadModel;
import pl.gregorymartin.b01.core.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository {
    List<TagReadModel> findAllAndMapToDto(Pageable page);
    Optional<Tag> findByTitle(String title);
    Tag save(Tag tag);
}
