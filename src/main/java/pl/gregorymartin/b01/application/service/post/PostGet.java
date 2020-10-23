package pl.gregorymartin.b01.application.service.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.gregorymartin.b01.core.mapping.PostMapper;
import pl.gregorymartin.b01.core.mapping.model.PostReadModel;
import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.core.repository.PostRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public
class PostGet {
    private static final int PAGE_SIZE = 25;
    private PostRepository postRepository;

    public PostGet(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostReadModel getPostDto(long id){
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent()){
            return PostMapper.mapPostEntityToSinglePostReadModel(post.get());
        }
        else throw new IllegalArgumentException("Post is not exists");
    }

    public List<PostReadModel> getPosts(int page, Sort.Direction sort, String sortBy, int items) {
        List<Post> list = postRepository.findAll(
                PageRequest.of(page, items,
                        Sort.by(sort, sortBy)
                )).getContent();

        return PostMapper.mapPostEntityToPostReadModel(list);
    }

    public long numberOfResultsFromSearchPost( String query){
        return postRepository.getSizeOfAllByContainedQuery(query);
    }
    public Page<PostReadModel> searchPosts(String query, int page, Sort.Direction sort, String sortBy, int items) {
        String[] queryArray = query.split(" ");


        List<Post> result = new ArrayList<>();
        for (String q : queryArray) {
            Page<Post> allByContainedQuery = postRepository.findAllByContainedQuery(q, PageRequest.of(page, items,
                    Sort.by(sort, sortBy)));

            result.addAll(allByContainedQuery.getContent());
        }
        List<PostReadModel> resultDto = result.stream()
                .distinct()
                .map(PostMapper::mapPostEntityToPostReadModel)
                .collect(Collectors.toList());

        PageImpl<PostReadModel> postReadModels = new PageImpl<>(
                resultDto, PageRequest.of(page, items,
                Sort.by(sort, sortBy)), items);

        return postReadModels;
    }
}

