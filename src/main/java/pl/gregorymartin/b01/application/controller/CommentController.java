package pl.gregorymartin.b01.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gregorymartin.b01.application.service.comment.CommentAdd;
import pl.gregorymartin.b01.application.service.comment.CommentModify;
import pl.gregorymartin.b01.application.service.post.PostAdd;
import pl.gregorymartin.b01.application.service.post.PostGet;
import pl.gregorymartin.b01.application.service.post.PostModify;
import pl.gregorymartin.b01.core.mapping.model.CommentWriteModel;
import pl.gregorymartin.b01.core.model.Comment;

import java.net.URI;

@RestController
@RequestMapping("/api")
class CommentController {
    private PostGet postGet;
    private PostAdd postAdd;
    private PostModify postModify;
    private CommentAdd commentAdd;
    private CommentModify commentModify;

    public CommentController(final PostGet postGet, final PostAdd postAdd, final PostModify postModify, final CommentAdd commentAdd, final CommentModify commentModify) {
        this.postGet = postGet;
        this.postAdd = postAdd;
        this.postModify = postModify;
        this.commentAdd = commentAdd;
        this.commentModify = commentModify;
    }
    @PostMapping("/comments")
    public ResponseEntity<Comment> addComment(@RequestBody CommentWriteModel comment) {
        Comment result = commentAdd.addComment(comment);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PatchMapping("/comments")
    public ResponseEntity<Comment> updatePost(@RequestParam long id, @RequestBody CommentWriteModel comment/*, Authentication authentication*/) {
        /*User user = (User) authentication.getPrincipal();
        comment.setUserId(user.getId());*/
        Comment result = commentModify.editCommentFromDao(id, comment);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }


    @DeleteMapping("/comments")
    public ResponseEntity deletePost(@RequestParam long id) {
        postModify.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
