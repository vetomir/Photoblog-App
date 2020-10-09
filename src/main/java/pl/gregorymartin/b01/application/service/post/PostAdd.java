package pl.gregorymartin.b01.application.service.post;

import org.springframework.stereotype.Service;
import pl.gregorymartin.b01.application.service.tag.TagAdd;
import pl.gregorymartin.b01.core.mapping.PostMapper;
import pl.gregorymartin.b01.core.mapping.model.PostWriteModel;
import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.core.repository.CommentRepository;
import pl.gregorymartin.b01.core.repository.PostRepository;
import pl.gregorymartin.b01.core.repository.TagRepository;

import java.util.stream.Collectors;

@Service
public
class PostAdd {
    private static final int PAGE_SIZE = 25;
    private PostRepository postRepository;
    private TagRepository tagRepository;
    private CommentRepository commentRepository;
    private TagAdd tagAdd;

    public PostAdd(final PostRepository postRepository, final TagRepository tagRepository, final CommentRepository commentRepository, final TagAdd tagAdd) {
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
        this.commentRepository = commentRepository;
        this.tagAdd = tagAdd;
    }



    public Post addPost(Post post) {
        /*post.setUser(userRepository.findById(authorId).get());*/

        post.setTags(
                post.getTags().stream()
                        .map( x -> tagAdd.addTags(x.getTitle())).collect(Collectors.toList()));

/*        User user = userRepository.findById(authorId).get();
        userRepository.save(user);*/

        return postRepository.save(post);
    }
    public Post addPostFromDao(PostWriteModel postWriteModel) {
        Post postToSave = PostMapper.mapDaoToEntity(postWriteModel);
        return addPost(postToSave);
    }
}
