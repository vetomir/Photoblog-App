package pl.gregorymartin.b01.view.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.gregorymartin.b01.application.service.post.PostGet;
import pl.gregorymartin.b01.application.service.tag.TagGet;
import pl.gregorymartin.b01.core.mapping.model.PostReadModel;
import pl.gregorymartin.b01.core.mapping.model.PostWriteModel;
import pl.gregorymartin.b01.core.mapping.model.TagReadModel;
import pl.gregorymartin.b01.view.controller.post.ListPattern;

import java.util.List;

@Controller
class SearchController {
    private PostGet postGet;
    private TagGet getTags;

    SearchController(final PostGet postGet, final TagGet getTags) {
        this.postGet = postGet;
        this.getTags = getTags;
    }

    @GetMapping("/search")
    String search(
            Model model,
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page
    ){
        int colums = 5;
        int rows = 5;
        Page<PostReadModel> postList = postGet.searchPosts(query, page, Sort.Direction.DESC, "id", colums * rows);
        boolean nextIsExist = postGet.searchPosts(query,page +1, Sort.Direction.DESC, "id",colums * rows).isEmpty();
        /*model.addAttribute("postList", postList.getContent());*/
        ListPattern.GroupList(model, postList.getContent(), 5);
        model.addAttribute("results", postGet.numberOfResultsFromSearchPost(query));
        model.addAttribute("query", query);
        model.addAttribute("isExist", !nextIsExist);

        List<TagReadModel> allTags = getTags.getAllTags();
        model.addAttribute("allTags", allTags);

        return "search";
    }

    @PostMapping("/search")
    String searchContent(
            @ModelAttribute("query") PostWriteModel search,
            Model model
    ){
        model.addAttribute("query", new PostWriteModel());
        return "redirect:/search?query=" + search.getDescription();
    }

}
