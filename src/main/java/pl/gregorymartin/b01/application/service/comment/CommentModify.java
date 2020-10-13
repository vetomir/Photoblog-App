package pl.gregorymartin.b01.application.service.comment;

import org.springframework.stereotype.Service;
import pl.gregorymartin.b01.core.mapping.CommentMapper;
import pl.gregorymartin.b01.core.mapping.model.CommentWriteModel;
import pl.gregorymartin.b01.core.model.Comment;
import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.core.repository.CommentRepository;
import pl.gregorymartin.b01.core.repository.PostRepository;

import javax.transaction.Transactional;

@Service
public
class CommentModify {
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public CommentModify(final PostRepository postRepository, final CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    public Comment editComment(Comment comment) {
        Comment commentEdit = commentRepository.findById(comment.getId()).orElseThrow();
        commentEdit.setContent(comment.getContent());
        return commentEdit;
    }

    @Transactional
    public Comment editCommentFromDao(long id, CommentWriteModel commentWriteModel) {
        Comment commentToSave = CommentMapper.mapCommentWriteModelToEntity(commentWriteModel);
        commentToSave.setId(id);
        return editComment(commentToSave);
    }

    public void deleteComment(long id) {
        Post post = commentRepository.findById(id).get().getPost();
        commentRepository.deleteById(id);
        post.setNumberOfComments(post.getComments().size());
        postRepository.save(post);
    }



}
