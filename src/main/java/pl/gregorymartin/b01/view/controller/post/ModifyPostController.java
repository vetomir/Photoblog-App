package pl.gregorymartin.b01.view.controller.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.gregorymartin.b01.application.service.post.PostAdd;
import pl.gregorymartin.b01.application.service.post.PostModify;
import pl.gregorymartin.b01.core.mapping.model.PostWriteModel;
import pl.gregorymartin.b01.core.model.Post;

import javax.validation.Valid;

@Controller
class ModifyPostController {
    private PostModify postModify;

    public ModifyPostController(final PostModify postModify) {
        this.postModify = postModify;
    }

    @PostMapping("/posts/edit")
    String editPost(
            @ModelAttribute("singlePost") PostWriteModel singlePost,
            @RequestParam int id,
            BindingResult bindingResult,
            Model model
    ) {
        if(bindingResult.hasErrors()){
            return "redirect:/";
        }
        Long resultId = postModify.editPostFromDao(id,singlePost).getId();

        model.addAttribute("message", "Done!");
        return "redirect:/posts?id=" + resultId;
    }
    @GetMapping("/posts/delete")
    String deletePost(
            @RequestParam(name = "id") long postId
    ){
        postModify.deletePost(postId);
        return "redirect:/";
    }
}
