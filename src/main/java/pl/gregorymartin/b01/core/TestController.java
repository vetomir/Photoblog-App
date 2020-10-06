package pl.gregorymartin.b01.core;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gregorymartin.b01.core.mapping.dto.PostDto;
import pl.gregorymartin.b01.core.model.post.Post;
import pl.gregorymartin.b01.core.model.post.SqlPostRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
class TestController {
    private SqlPostRepository sqlPostRepository;

    public TestController(final SqlPostRepository sqlPostRepository) {
        this.sqlPostRepository = sqlPostRepository;
        sqlPostRepository.save(new Post("Elo", "asdfg"));
    }

    @GetMapping
    List<PostDto> elo(){
        return sqlPostRepository.findAllAndMapToDto();
    }
    @GetMapping("/{id}")
    PostDto elo(@PathVariable long id){
        return sqlPostRepository.findPostByIdAndMapToDto(id).get();
    }
}
