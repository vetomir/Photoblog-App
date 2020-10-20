package pl.gregorymartin.b01.application.service.tag;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.gregorymartin.b01.core.mapping.model.TagReadModel;
import pl.gregorymartin.b01.core.repository.TagRepository;

import java.util.List;

@Service
public
class TagGet {
    private static final int PAGE_SIZE = 25;
    private TagRepository tagRepository;

    public TagGet(final TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }
    public List<TagReadModel> getTags(int page, Sort.Direction sort, String sortBy) {
        return tagRepository.findAllPageAndMapToDto(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, sortBy)
                )
        );
    }
    public List<TagReadModel> getAllTags() {
        return tagRepository.findAllAndMapToDto();
    }
}

