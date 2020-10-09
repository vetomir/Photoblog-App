package pl.gregorymartin.b01.application.service.tag;

import org.springframework.stereotype.Service;
import pl.gregorymartin.b01.core.model.Tag;
import pl.gregorymartin.b01.core.repository.TagRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public
class TagAdd {
    private TagRepository tagRepository;

    public TagAdd(final TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }
    @Transactional
    public Tag addTags(String tag) {
        Optional<Tag> result = tagRepository.findByTitle(tag);
        if (result.isEmpty()) {
            return tagRepository.save(new Tag(tag));
        } else{
            result.get().setNumberOfPosts(result.get().getNumberOfPosts() + 1);
            return tagRepository.save(result.get());
        }

    }
}
