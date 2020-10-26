package pl.gregorymartin.b01.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.gregorymartin.b01.application.service.comment.CommentAdd;
import pl.gregorymartin.b01.application.service.comment.CommentModify;
import pl.gregorymartin.b01.application.service.post.PostModify;
import pl.gregorymartin.b01.core.mapping.model.CommentWriteModel;
import pl.gregorymartin.b01.core.mapping.model.PostWriteModel;

import javax.validation.Valid;

@Controller
class CommentViewController {
    private CommentAdd commentAdd;
    private CommentModify commentModify;

    public CommentViewController(final CommentAdd commentAdd, final CommentModify commentModify) {
        this.commentAdd = commentAdd;
        this.commentModify = commentModify;
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

    @GetMapping("/comments/delete")
    String deleteComment(
            @RequestParam int postId,
            @RequestParam int commentId

    ){
        commentModify.deleteComment(commentId);
        return "redirect:/posts?id=" + postId;
    }
}
