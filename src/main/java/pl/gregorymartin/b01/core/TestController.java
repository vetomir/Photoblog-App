package pl.gregorymartin.b01.core;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gregorymartin.b01.application.service.post.PostAdd;
import pl.gregorymartin.b01.application.service.post.PostGet;
import pl.gregorymartin.b01.core.mapping.dao.PostSave;
import pl.gregorymartin.b01.core.mapping.dto.PostList;
import pl.gregorymartin.b01.core.mapping.dto.PostSingle;
import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.core.repository.SqlPostRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
class TestController {
    private SqlPostRepository sqlPostRepository;
    private PostGet postGet;
    private PostAdd addPost;

    public TestController(final SqlPostRepository sqlPostRepository, final PostGet postGet, final PostAdd addPost) {
        this.sqlPostRepository = sqlPostRepository;
        this.postGet = postGet;
        this.addPost = addPost;

        addPost.addPostFromDao(new PostSave("afdsasgagd", "dsfsghfg #CHUJ"));
        addPost.addPostFromDao(new PostSave("afdsgasfad", "dsfsghfg #pizda"));
        addPost.addPostFromDao(new PostSave("afdsgasdgd", "dsfsghfg #Cipa"));
        sqlPostRepository.save(new Post("Elsao", "asdfdsgg"));
        sqlPostRepository.save(new Post("Elasgo", "asddsfsfg"));
        sqlPostRepository.save(new Post("Elo", "elo"));
        sqlPostRepository.save(new Post("Elasfo", "elo"));
    }

    @GetMapping("/all")
    List<PostList> elo(){
        return postGet.getPosts(0, Sort.Direction.ASC, "id");
    }

    @GetMapping("/tak")
    String tak(){
        return "TAK TAK";
    }
    @GetMapping("/{id}")
    PostSingle elo(@PathVariable long id){
        return postGet.getPostDto(id);
    }
}
