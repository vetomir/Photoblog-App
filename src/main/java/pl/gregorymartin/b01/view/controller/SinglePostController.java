package pl.gregorymartin.b01.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.gregorymartin.b01.application.service.post.PostGet;
import pl.gregorymartin.b01.application.service.tag.TagGet;
import pl.gregorymartin.b01.core.mapping.model.CommentWriteModel;

@Controller
class SinglePostController {
    private PostGet getPosts;
    private TagGet getTags;


    public SinglePostController(final PostGet getPosts, final TagGet getTags) {
        this.getPosts = getPosts;
        this.getTags = getTags;
    }

    @GetMapping("/posts")
    String singlePost(
            Model model,
            @RequestParam long id
    ){
        model.addAttribute("newComment", new CommentWriteModel());
        model.addAttribute("post", getPosts.getPostDto(id));
        return "single-post";
    }

}
