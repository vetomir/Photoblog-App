package pl.gregorymartin.b01.core.repository;

import pl.gregorymartin.b01.core.mapping.dto.PostSingle;
import pl.gregorymartin.b01.core.model.Tag;

import java.util.Optional;

public interface TagRepository {
    Optional<Tag> findByTitle(String title);
    Tag save(Tag tag);
}
