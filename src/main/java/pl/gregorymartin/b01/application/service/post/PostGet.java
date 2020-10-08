package pl.gregorymartin.b01.application.service.post;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.gregorymartin.b01.core.mapping.dao.CommentSave;
import pl.gregorymartin.b01.core.mapping.dao.PostDaoMapper;
import pl.gregorymartin.b01.core.mapping.dao.PostSave;
import pl.gregorymartin.b01.core.mapping.dto.PostDtoMapper;
import pl.gregorymartin.b01.core.mapping.dto.PostList;
import pl.gregorymartin.b01.core.mapping.dto.PostSingle;
import pl.gregorymartin.b01.core.mapping.dto.TagDto;
import pl.gregorymartin.b01.core.model.Comment;
import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.core.model.Tag;
import pl.gregorymartin.b01.core.repository.CommentRepository;
import pl.gregorymartin.b01.core.repository.PostRepository;
import pl.gregorymartin.b01.core.repository.TagRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public
class PostGet {
    private static final int PAGE_SIZE = 25;
    private PostRepository postRepository;

    public PostGet(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostSingle getPostDto(long id){
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent()){
            return PostDtoMapper.mapToPostDto(post.get());
        }
        else throw new IllegalArgumentException("Post is not exists");
    }

    public List<PostList> getPosts(int page, Sort.Direction sort, String sortBy) {
        return postRepository.findAllAndMapToDto(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, sortBy)
                )
        );
    }

    public List<PostList> searchPosts(String query, int page, int PAGE_SIZE) {
        String[] queryArray = query.split(" ");

        List<Post> result = new ArrayList<>();
        for (String q : queryArray) {
            postRepository.findAll().stream()
                    .filter(x -> x.getDescription().toUpperCase().contains(q.toUpperCase()))
                    .forEach(result::add);
        }
        List<PostList> resultDto = result.stream()
                .distinct()
                .map(x -> new PostList(x.getDescription(), x.getPhotoUrl(), x.getCreatedOn()))
                .collect(Collectors.toList());


        int listSize = result.size();

        //fixing 5XX, with insufficient number of posts in page
        if ((page * PAGE_SIZE) + PAGE_SIZE < listSize) {
            return resultDto.subList(page * PAGE_SIZE, (page * PAGE_SIZE) + PAGE_SIZE);
        }
        if (page * PAGE_SIZE == listSize) {
            return new ArrayList<>();
        } else
            return resultDto.subList(page * PAGE_SIZE, listSize);

    }
}

