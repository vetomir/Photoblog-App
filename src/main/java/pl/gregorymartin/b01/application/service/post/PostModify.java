package pl.gregorymartin.b01.application.service.post;

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
class PostModify {
    private static final int PAGE_SIZE = 25;
    private PostRepository postRepository;
    private TagRepository tagRepository;

    public PostModify(final PostRepository postRepository, final TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
    }

    @Transactional
    public Post editPost(Post post) {
        Post postEdited = postRepository.findById(post.getId()).orElseThrow();
        postEdited.setDescription(post.getDescription());
        return postEdited;
    }

    @Transactional
    public Post editPostFromDao(PostSave postDao) {
        Post postToSave = PostDaoMapper.mapDaoToEntity(postDao);
        return editPost(postToSave);
    }

    public void deletePost(long id) {
        postRepository.deleteById(id);
    }
}
