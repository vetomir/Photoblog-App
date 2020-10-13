package pl.gregorymartin.b01.application.service.comment;

import org.springframework.stereotype.Service;
import pl.gregorymartin.b01.core.mapping.model.CommentWriteModel;
import pl.gregorymartin.b01.core.mapping.model.CommentReadModel;
import pl.gregorymartin.b01.core.mapping.CommentMapper;
import pl.gregorymartin.b01.core.model.Comment;
import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.core.repository.CommentRepository;
import pl.gregorymartin.b01.core.repository.PostRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public
class CommentGet {
    private static final int PAGE_SIZE = 25;
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public CommentGet(final PostRepository postRepository, final CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public List<CommentReadModel> getCommentsfromPost(long postId){
        List<Comment> comments = postRepository.findById(postId).get().getComments();
        return CommentMapper.mapEntityToCommentReadModel(comments);
    }

    @Transactional
    public Comment addComment(CommentWriteModel commentWriteModel) {
        /*Optional<User> user = userRepository.findById(commentDao.getUserId());*/
        Optional<Post> post = postRepository.findById(commentWriteModel.getPostId());

        if (/*user.isPresent() && */post.isPresent()){
            Comment obj = new Comment(commentWriteModel.getContent());
            obj.setPost(post.get());
            /*obj.setUser(user.get());*/

            post.get().setNumberOfComments(obj.getPost().getComments().size() + 1);
            postRepository.save(post.get());

            return commentRepository.save(obj);
        }
        else
            return null;
    }
}
