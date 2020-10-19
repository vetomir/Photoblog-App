package pl.gregorymartin.b01.application.service.post;

import org.springframework.stereotype.Service;
import pl.gregorymartin.b01.application.service.tag.TagAdd;
import pl.gregorymartin.b01.core.mapping.PostMapper;
import pl.gregorymartin.b01.core.mapping.model.PostReadModel;
import pl.gregorymartin.b01.core.mapping.model.PostWriteModel;
import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.core.repository.PostRepository;
import pl.gregorymartin.b01.security.model.User;
import pl.gregorymartin.b01.security.repository.UserRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public
class PostAdd {
    private PostRepository postRepository;
    private TagAdd tagAdd;
    private UserRepository userRepository;

    public PostAdd(final PostRepository postRepository, final TagAdd tagAdd, final UserRepository userRepository) {
        this.postRepository = postRepository;
        this.tagAdd = tagAdd;
        this.userRepository = userRepository;
    }

    public PostReadModel addPost(Post post) {
        /*post.setUser(userRepository.findById(authorId).get());*/

        post.setTags(
                post.getTags().stream()
                        .map( x -> tagAdd.addTags(x.getTitle())).collect(Collectors.toList()));

/*        User user = userRepository.findById(authorId).get();
        userRepository.save(user);*/

        return PostMapper.mapPostEntityToPostReadModel(postRepository.save(post));
    }

    public PostReadModel addPostFromDao(PostWriteModel postWriteModel) {
        Optional<User> user = userRepository.findById(postWriteModel.getUserId());
        if(user.isEmpty()){
           throw new IllegalArgumentException("User is not present.");
        }
        Post postToSave = PostMapper.mapPostWriteModelsToEntity(postWriteModel);
        postToSave.setUser(user.get());
        return addPost(postToSave);
    }
}
