package pl.gregorymartin.b01.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gregorymartin.b01.core.model.Tag;

@Repository
public interface SqlTagRepository extends TagRepository, JpaRepository<Tag, Long> {
}
