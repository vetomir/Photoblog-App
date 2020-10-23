package pl.gregorymartin.b01.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.gregorymartin.b01.application.service.comment.CommentAdd;
import pl.gregorymartin.b01.application.service.post.PostAdd;
import pl.gregorymartin.b01.core.mapping.model.CommentWriteModel;
import pl.gregorymartin.b01.core.mapping.model.PostWriteModel;

import javax.validation.Valid;

@Controller
class CreateCommentController {
    private CommentAdd commentAdd;

    public CreateCommentController(final CommentAdd commentAdd) {
        this.commentAdd = commentAdd;
    }

    @PostMapping("/comments/submit")
    String createPost(
            @ModelAttribute("newComment") @Valid CommentWriteModel newComment,
            BindingResult bindingResult,
            Model model,
            @RequestParam int postId
            //proncipal
    ) {
        if(bindingResult.hasErrors()){
            return "single-post";
        }

        model.addAttribute("newComment", new CommentWriteModel());
        newComment.setPostId(postId);
        newComment.setUserId(1L);
        commentAdd.addComment(newComment);

        //todo model.addAttribute("message", "Git Majonez!");
        return "redirect:/posts?id=" + postId;
    }

}
