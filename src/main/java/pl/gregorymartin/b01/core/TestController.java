package pl.gregorymartin.b01.core;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gregorymartin.b01.application.service.PostService;
import pl.gregorymartin.b01.core.mapping.dto.PostList;
import pl.gregorymartin.b01.core.mapping.dto.PostSingle;
import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.core.repository.SqlPostRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
class TestController {
    private SqlPostRepository sqlPostRepository;
    private PostService postService;

    public TestController(final SqlPostRepository sqlPostRepository, final PostService postService) {
        this.sqlPostRepository = sqlPostRepository;
        this.postService = postService;
        sqlPostRepository.save(new Post("Elo", "asdfdsgg"));
        sqlPostRepository.save(new Post("Elo", "asddsfsfg"));
    }

    @GetMapping("/all")
    List<PostList> elo(){
        return postService.getPosts(0, Sort.Direction.ASC, "id");
    }

    @GetMapping("/tak")
    String tak(){
        return "TAK";
    }
    @GetMapping("/{id}")
    PostSingle elo(@PathVariable long id){
        return sqlPostRepository.findPostByIdAndMapToDto(id).get();
    }
}
