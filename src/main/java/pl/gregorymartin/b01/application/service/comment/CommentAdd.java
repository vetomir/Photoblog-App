package pl.gregorymartin.b01.application.service.comment;

import org.springframework.stereotype.Service;
import pl.gregorymartin.b01.core.mapping.CommentMapper;
import pl.gregorymartin.b01.core.mapping.model.CommentReadModel;
import pl.gregorymartin.b01.core.mapping.model.CommentWriteModel;
import pl.gregorymartin.b01.core.model.Comment;
import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.core.repository.CommentRepository;
import pl.gregorymartin.b01.core.repository.PostRepository;
import pl.gregorymartin.b01.security.model.User;
import pl.gregorymartin.b01.security.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public
class CommentAdd {

    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private UserRepository userRepository;

    public CommentAdd(final PostRepository postRepository, final CommentRepository commentRepository, final UserRepository userRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public CommentReadModel addComment(CommentWriteModel commentWriteModel) {
        Optional<User> user = userRepository.findById(commentWriteModel.getUserId());
        Optional<Post> post = postRepository.findById(commentWriteModel.getPostId());

        if (user.isPresent() && post.isPresent()){
            Comment obj = new Comment(commentWriteModel.getContent());
            obj.setUser(user.get());
            obj.setPost(post.get());

            post.get().setNumberOfComments(obj.getPost().getComments().size() + 1);
            postRepository.save(post.get());

            return CommentMapper.mapEntityToCommentReadModel(commentRepository.save(obj));
        }
        else
            return null;
    }
}
