package pl.gregorymartin.b01.application.controller;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gregorymartin.b01.application.service.post.PostAdd;
import pl.gregorymartin.b01.application.service.post.PostGet;
import pl.gregorymartin.b01.application.service.post.PostModify;
import pl.gregorymartin.b01.core.mapping.model.PostWriteModel;
import pl.gregorymartin.b01.core.mapping.model.PostInListReadModel;
import pl.gregorymartin.b01.core.mapping.model.PostReadModel;
import pl.gregorymartin.b01.core.model.Post;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
class PostController {
    private PostGet postGet;
    private PostAdd postAdd;
    private PostModify postModify;

    public PostController(final PostGet postGet, final PostAdd postAdd, final PostModify postModify) {
        this.postGet = postGet;
        this.postAdd = postAdd;
        this.postModify = postModify;
    }

    @GetMapping("/list")
    public ResponseEntity<List<PostReadModel>> readPosts(@RequestParam(required = false) Integer page, Sort.Direction sort, String sortBy
                                                  /*@AuthenticationPrincipal UsernamePasswordAuthenticationToken user*/) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        String sortByVariable = sortBy != null ? sortBy : "id";

        return ResponseEntity.ok(postGet.getPosts(pageNumber, sortDirection, sortByVariable, 25));
    }

    @GetMapping
    public ResponseEntity<PostReadModel> readSinglePost(@RequestParam int id) {
        return ResponseEntity.ok(postGet.getPostDto(id));
    }

    @PostMapping
    public ResponseEntity<PostReadModel> createPost(@RequestBody PostWriteModel post/*, @RequestParam(name = "user-id") long userId*/) {
        PostReadModel result = postAdd.addPostFromDao(post);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PatchMapping
    public ResponseEntity<Post> updatePost(@RequestParam long id, @RequestBody PostWriteModel post/*, @RequestParam(name = "user-id") long userId*/) {
        Post result = postModify.editPostFromDao(id, post);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @DeleteMapping
    public ResponseEntity deletePost(@RequestParam long id) {
        postModify.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
