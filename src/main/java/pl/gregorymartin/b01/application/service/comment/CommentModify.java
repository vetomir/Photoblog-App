package pl.gregorymartin.b01.application.service.comment;

import org.springframework.stereotype.Service;
import pl.gregorymartin.b01.core.mapping.dao.CommentSave;
import pl.gregorymartin.b01.core.model.Comment;
import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.core.repository.CommentRepository;
import pl.gregorymartin.b01.core.repository.PostRepository;
import pl.gregorymartin.b01.core.repository.TagRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public
class CommentModify {
    private static final int PAGE_SIZE = 25;
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public CommentModify(final PostRepository postRepository, final CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public void deleteComment(long id) {
        Post post = commentRepository.findById(id).get().getPost();
        commentRepository.deleteById(id);
        post.setNumberOfComments(post.getComments().size());
        postRepository.save(post);
    }



}
