package pl.gregorymartin.b01.application.service.tag;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.gregorymartin.b01.core.mapping.dto.PostList;
import pl.gregorymartin.b01.core.mapping.dto.TagDto;
import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.core.repository.CommentRepository;
import pl.gregorymartin.b01.core.repository.PostRepository;
import pl.gregorymartin.b01.core.repository.TagRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public
class TagGet {
    private static final int PAGE_SIZE = 25;
    private TagRepository tagRepository;

    public TagGet(final TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }
    public List<TagDto> getTags(int page, Sort.Direction sort, String sortBy) {
        return tagRepository.findAllAndMapToDto(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, sortBy)
                )
        );
    }
}

