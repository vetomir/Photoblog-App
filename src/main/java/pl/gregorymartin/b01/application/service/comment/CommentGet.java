package pl.gregorymartin.b01.application.service.comment;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import java.util.List;
import java.util.Optional;

@Service
public
class CommentGet {
    private static final int PAGE_SIZE = 25;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentGet(final PostRepository postRepository, final CommentRepository commentRepository, final UserRepository userRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public List<CommentReadModel> getCommentsFromPost(long postId, int page, Sort.Direction sort, String sortBy){
        List<Comment> comments = postRepository.findAllCommentsByPostId(
                postId,
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, sortBy)
                )).getContent();

        return CommentMapper.mapEntityToCommentReadModel(comments);
    }

    @Transactional
    public Comment addComment(CommentWriteModel commentWriteModel) {
        Optional<User> user = userRepository.findById(commentWriteModel.getUserId());
        Optional<Post> post = postRepository.findById(commentWriteModel.getPostId());

        if (user.isPresent() && post.isPresent()){
            Comment obj = new Comment(commentWriteModel.getContent());
            obj.setPost(post.get());
            obj.setUser(user.get());

            post.get().setNumberOfComments(obj.getPost().getComments().size() + 1);
            postRepository.save(post.get());

            return commentRepository.save(obj);
        }
        else
            return null;
    }
}
