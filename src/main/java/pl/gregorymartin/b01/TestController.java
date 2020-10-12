package pl.gregorymartin.b01;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gregorymartin.b01.application.service.comment.CommentAdd;
import pl.gregorymartin.b01.application.service.post.PostAdd;
import pl.gregorymartin.b01.application.service.post.PostGet;
import pl.gregorymartin.b01.core.mapping.model.CommentWriteModel;
import pl.gregorymartin.b01.core.mapping.model.PostWriteModel;
import pl.gregorymartin.b01.core.mapping.model.PostInListReadModel;
import pl.gregorymartin.b01.core.mapping.model.PostReadModel;
import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.core.repository.sql.SqlPostRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
class TestController {
    private SqlPostRepository sqlPostRepository;
    private PostGet postGet;
    private PostAdd addPost;
    private CommentAdd commentAdd;

    public TestController(final SqlPostRepository sqlPostRepository, final PostGet postGet, final PostAdd addPost, final CommentAdd commentAdd) {
        this.sqlPostRepository = sqlPostRepository;
        this.postGet = postGet;
        this.addPost = addPost;
        this.commentAdd = commentAdd;

        addPost.addPostFromDao(new PostWriteModel("!!!!!sa!!!!!!!dfafhaf!!!!!!!", "dsfsghfg #CHUJ"));
        addPost.addPostFromDao(new PostWriteModel("dsasasfs", "dssadasfsghfg #pizda"));
        addPost.addPostFromDao(new PostWriteModel("!!!!!!!as!!!dfdsa!!!!!", "dsfsghfg #Cipa"));
        commentAdd.addComment(new CommentWriteModel("sadfsdaafdsfSsd"));
    }

    @GetMapping("/all")
    List<PostInListReadModel> elo(){
        return postGet.getPosts(0, Sort.Direction.ASC, "id");
    }

    @GetMapping("/tak")
    String tak(){
        return "TAK TAK";
    }
    @GetMapping("/{id}")
    PostReadModel elo(@PathVariable long id){
        return postGet.getPostDto(id);
    }
}
