package pl.gregorymartin.b01.application.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.gregorymartin.b01.core.mapping.dao.CommentSave;
import pl.gregorymartin.b01.core.mapping.dao.PostDaoMapper;
import pl.gregorymartin.b01.core.mapping.dao.PostSave;
import pl.gregorymartin.b01.core.mapping.dto.PostList;
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
class PostService {
    private static final int PAGE_SIZE = 25;
    private PostRepository postRepository;
    private TagRepository tagRepository;
    private CommentRepository commentRepository;

    public PostService(final PostRepository postRepository, final TagRepository tagRepository, final CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
        this.commentRepository = commentRepository;
    }

    public List<PostList> getPosts(int page, Sort.Direction sort, String sortBy) {
        return postRepository.findAllAndMapToDto(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, sortBy)
                )
        );
    }

    public List<PostList> findAllPosts(){
        return postRepository
                .findAllAndMapToDto(PageRequest.of(0,  99999, Sort.by(Sort.Direction.DESC,"id") ));
    }

    public List<PostList> searchPosts(String query, int page, int PAGE_SIZE){
        String[] queryArray = query.split(" ");

        List<Post> result = new ArrayList<>();
        for(String q : queryArray){
            postRepository.findAll().stream()
                    .filter(x -> x.getDescription().toUpperCase().contains(q.toUpperCase()))
                    .forEach(result::add);
        }
        List<PostList> resultDto = result.stream()
                .distinct()
                .map( x -> new PostList(x.getDescription(),x.getPhotoUrl(), x.getCreatedOn()))
                .collect(Collectors.toList());


        int listSize = result.size();

        //fixing 5XX with insufficient number of posts in page
        if((page * PAGE_SIZE) + PAGE_SIZE < listSize){
            return resultDto.subList(page * PAGE_SIZE, (page * PAGE_SIZE) + PAGE_SIZE);
        }
        if(page * PAGE_SIZE == listSize){
            return new ArrayList<>();
        }
        else
            return resultDto.subList(page * PAGE_SIZE, listSize);

    }

    public Post addPost(Long authorId, Post post) {
        /*post.setUser(userRepository.findById(authorId).get());*/

        post.setTags(
                post.getTags().stream()
                        .map( x -> this.addTags(x.getTitle())).collect(Collectors.toList()));

/*        User user = userRepository.findById(authorId).get();
        userRepository.save(user);*/

        return postRepository.save(post);
    }
    public Post addPostFromDao(PostSave postDao) {
        Post postToSave = PostDaoMapper.mapDaoToEntity(postDao);
        return addPost(postDao.getUserId(), postToSave);
    }

    @Transactional
    public Tag addTags(String tag) {
        Optional<Tag> result = tagRepository.findByTitle(tag);
        if (result.isEmpty()) {
            return tagRepository.save(new Tag(tag));
        } else{
            result.get().setNumberOfPosts(result.get().getNumberOfPosts() + 1);
            return tagRepository.save(result.get());
        }

    }

    @Transactional
    public Comment addComment(/*Long authorId,*/ Long postId, CommentSave commentDao) {
        /*Optional<User> user = userRepository.findById(authorId);*/
        Optional<Post> post = postRepository.findById(postId);

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
