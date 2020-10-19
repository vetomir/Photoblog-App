package pl.gregorymartin.b01.view.controller;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.gregorymartin.b01.application.service.post.PostGet;
import pl.gregorymartin.b01.application.service.tag.TagGet;
import pl.gregorymartin.b01.core.mapping.model.PostInListReadModel;
import pl.gregorymartin.b01.core.mapping.model.PostReadModel;
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
        List<PostReadModel> allPosts = getPosts.getPosts(page, Sort.Direction.DESC, "id");
        boolean nextIsExist = getPosts.getPosts(page +1, Sort.Direction.DESC, "id").isEmpty();

        List<TagReadModel> allTags = getTags.getTags(page, Sort.Direction.DESC, "id");
        model.addAttribute("popularTags", allTags.subList(0,9));
        model.addAttribute("allTags", allTags.subList(9, allTags.size()));

        model.addAttribute("allPosts", allPosts);
        model.addAttribute("isExist", !nextIsExist);
        return "home";
    }

}
