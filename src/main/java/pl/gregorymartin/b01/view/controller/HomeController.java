package pl.gregorymartin.b01.view.controller;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.gregorymartin.b01.application.service.post.PostGet;
import pl.gregorymartin.b01.application.service.tag.TagGet;
import pl.gregorymartin.b01.core.mapping.model.PostReadModel;
import pl.gregorymartin.b01.core.mapping.model.PostWriteModel;
import pl.gregorymartin.b01.core.mapping.model.TagReadModel;

import java.util.List;

@Controller
class HomeController {
    private PostGet getPosts;
    private TagGet getTags;


    public HomeController(final PostGet getPosts, final TagGet getTags) {
        this.getPosts = getPosts;
        this.getTags = getTags;
    }

    @GetMapping
    String home(
            Model model,
            @RequestParam(defaultValue = "0") int page
    ){
        List<TagReadModel> allTags = getTags.getAllTags();
        model.addAttribute("allTags", allTags);

        int columns = 5;
        int rows = 5;

        List<PostReadModel> allPosts = getPosts.getPosts(page, Sort.Direction.DESC, "id", columns * rows);
        PostPattern.GroupList(model, allPosts, columns);

        model.addAttribute("allPosts", allPosts);
        model.addAttribute("newPost", new PostWriteModel());
        model.addAttribute("query", new PostWriteModel());
        return "home";
    }
    @GetMapping("/loading")
    String loading(
    ){
        return "loading";
    }



}
