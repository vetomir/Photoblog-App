package pl.gregorymartin.b01.application.service.post;

import org.springframework.stereotype.Service;
import pl.gregorymartin.b01.core.mapping.PostMapper;
import pl.gregorymartin.b01.core.mapping.model.PostWriteModel;
import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.core.repository.PostRepository;
import pl.gregorymartin.b01.core.repository.TagRepository;

import javax.transaction.Transactional;

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
    public Post editPostFromDao(long id, PostWriteModel postWriteModel) {
        Post postToSave = PostMapper.mapDaoToEntity(postWriteModel);
        postToSave.setId(id);
        return editPost(postToSave);
    }

    public void deletePost(long id) {
        postRepository.deleteById(id);
    }
}
