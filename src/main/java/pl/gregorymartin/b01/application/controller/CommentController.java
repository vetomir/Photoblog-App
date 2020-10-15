package pl.gregorymartin.b01.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.gregorymartin.b01.application.service.comment.CommentAdd;
import pl.gregorymartin.b01.application.service.comment.CommentModify;
import pl.gregorymartin.b01.application.service.post.PostAdd;
import pl.gregorymartin.b01.application.service.post.PostGet;
import pl.gregorymartin.b01.application.service.post.PostModify;
import pl.gregorymartin.b01.core.mapping.model.CommentReadModel;
import pl.gregorymartin.b01.core.mapping.model.CommentWriteModel;
import pl.gregorymartin.b01.core.model.Comment;
import pl.gregorymartin.b01.security.model.User;

import java.net.URI;

@RestController
@RequestMapping("/api/comments")
class CommentController {
    private PostModify postModify;
    private CommentAdd commentAdd;
    private CommentModify commentModify;

    public CommentController(final PostModify postModify, final CommentAdd commentAdd, final CommentModify commentModify) {
        this.postModify = postModify;
        this.commentAdd = commentAdd;
        this.commentModify = commentModify;
    }
    @PostMapping
    public ResponseEntity<CommentReadModel> createComment(@RequestBody CommentWriteModel comment, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        comment.setUserId(user.getId());
        CommentReadModel result = commentAdd.addComment(comment);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PatchMapping
    public ResponseEntity<Comment> updatePost(@RequestParam long id, @RequestBody CommentWriteModel comment, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        comment.setUserId(user.getId());
        Comment result = commentModify.editCommentFromDao(id, comment);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }


    @DeleteMapping
    public ResponseEntity deletePost(@RequestParam long id) {
        postModify.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
