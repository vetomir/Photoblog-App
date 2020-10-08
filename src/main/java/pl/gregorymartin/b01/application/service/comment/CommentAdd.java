package pl.gregorymartin.b01.application.service.comment;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.gregorymartin.b01.core.mapping.dao.CommentSave;
import pl.gregorymartin.b01.core.mapping.dao.PostDaoMapper;
import pl.gregorymartin.b01.core.mapping.dao.PostSave;
import pl.gregorymartin.b01.core.mapping.dto.PostList;
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
class CommentAdd {
    private static final int PAGE_SIZE = 25;
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public CommentAdd(final PostRepository postRepository, final CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    public Comment addComment(CommentSave commentDao) {
        /*Optional<User> user = userRepository.findById(commentDao.getUserId());*/
        Optional<Post> post = postRepository.findById(commentDao.getPostId());

        if (/*user.isPresent() && */post.isPresent()){
            Comment obj = new Comment(commentDao.getContent());
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