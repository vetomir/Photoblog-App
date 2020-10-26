package pl.gregorymartin.b01.view.controller.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.gregorymartin.b01.application.service.post.PostAdd;
import pl.gregorymartin.b01.application.service.post.PostGet;
import pl.gregorymartin.b01.application.service.tag.TagGet;
import pl.gregorymartin.b01.core.mapping.model.PostReadModel;
import pl.gregorymartin.b01.core.mapping.model.PostWriteModel;

import javax.validation.Valid;

@Controller
class CreatePostController {
    private PostAdd postAdd;

    public CreatePostController(final PostAdd postAdd) {
        this.postAdd = postAdd;
    }

    @GetMapping("/posts/submit")
    String createPostTab(
            Model model
    ){
        model.addAttribute("newPost", new PostWriteModel());
        return "create-post";
    }

    @PostMapping("/submit")
    String createPost(
            @ModelAttribute("newPost") @Valid PostWriteModel newPost,
            BindingResult bindingResult,
            Model model
            //proncipal
    ) {
        if(bindingResult.hasErrors()){
            return "create-post";
        }

        model.addAttribute("newPost", new PostWriteModel());
        newPost.setUserId(1L);
        Long postId = postAdd.addPostFromDao(newPost).getId();

        //todo model.addAttribute("message", "Git Majonez!");
        return "redirect:/posts?id=" + postId;
    }

}
