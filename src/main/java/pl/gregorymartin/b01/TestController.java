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
import pl.gregorymartin.b01.core.repository.sql.SqlPostRepository;
import pl.gregorymartin.b01.security.mapping.model.RoleWriteModel;
import pl.gregorymartin.b01.security.mapping.model.UserWriteModel;
import pl.gregorymartin.b01.security.service.role.RoleAdd;
import pl.gregorymartin.b01.security.service.user.UserAdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api")
class TestController {
    private SqlPostRepository sqlPostRepository;
    private PostGet postGet;
    private PostAdd addPost;
    private CommentAdd commentAdd;
    private UserAdd userAdd;
    private RoleAdd roleAdd;

    public TestController(final SqlPostRepository sqlPostRepository, final PostGet postGet, final PostAdd addPost, final CommentAdd commentAdd, final UserAdd userAdd, final RoleAdd roleAdd) {
        this.sqlPostRepository = sqlPostRepository;
        this.postGet = postGet;
        this.addPost = addPost;
        this.commentAdd = commentAdd;
        this.userAdd = userAdd;
        this.roleAdd = roleAdd;

        /*addContent(300);*/
    }

    public String helloLipsum(String a, String b, String c){
        return "Ut enim ad minim "+ a + " veniam, quis nostrud " + b + " exercitation ullamco laboris nisi ut aliquip " + c + " ex ea commodo consequat.";
    }
    String lipsum = "Lorem ipsum dolor sit amet enim. Curabitur et ligula. Ut molestie a, ultricies porta urna. Vestibulum commodo volutpat a, convallis ac, laoreet enim. Phasellus fermentum in, dolor. Pellentesque facilisis.";

    void addContent(int number) {
        roleAdd.addRole(new RoleWriteModel("ROLE_USER"));
        //
        userAdd.registerUser(new UserWriteModel("szczepanus","test1@test.pl", "Test123", "Test123"));
        userAdd.registerUser(new UserWriteModel("mirex","test2@test.pl", "Test123", "Test123"));
        userAdd.registerUser(new UserWriteModel("juzkus","test3@test.pl", "Test123", "Test123"));
        userAdd.registerUser(new UserWriteModel("władomit","test4@test.pl", "Test123", "Test123"));
        userAdd.registerUser(new UserWriteModel("alfons","test5@test.pl", "Test123", "Test123"));
        userAdd.registerUser(new UserWriteModel("elmo","test6@test.pl", "Test123", "Test123"));
        userAdd.registerUser(new UserWriteModel("tadeusix","test7@test.pl", "Test123", "Test123"));
        //
        List<String> photos = new ArrayList<>();
        photos.add("https://images.unsplash.com/photo-1504829857797-ddff29c27927?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=10");
        photos.add("https://images.unsplash.com/photo-1473654729523-203e25dfda10?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=10");
        photos.add("https://images.unsplash.com/photo-1473654729523-203e25dfda10?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=10");
        photos.add("https://images.unsplash.com/photo-1456298964505-ef9e1a638209?ixlib=rb-1.2.1&auto=format&fit=crop&w=1934&q=80");
        photos.add("https://images.unsplash.com/photo-1528837516156-0a7225a43641?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2091&q=10");
        photos.add("https://images.unsplash.com/photo-1520475178495-a8d5b36f1782?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1938&q=10");
        photos.add("https://images.unsplash.com/photo-1495250357898-6822052ef5b8?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=10");
        photos.add("https://images.unsplash.com/photo-1475139977302-d5bd42365d8b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1953&q=10");
        photos.add("https://images.unsplash.com/photo-1573425153374-392bb1f398da?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=10");
        photos.add("https://images.unsplash.com/photo-1573424659108-f3b6cfaebdd3?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=10");
        photos.add("https://images.unsplash.com/photo-1541622127521-161810c66731?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1267&q=10");
        photos.add("https://images.unsplash.com/photo-1523437026634-0a74ec146ae2?ixlib=rb-1.2.1&auto=format&fit=crop&w=1951&q=10");
        photos.add("https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=10");
        photos.add("https://images.unsplash.com/photo-1478465726282-ddb11650c80b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2134&q=10");
        photos.add("https://images.unsplash.com/photo-1481824429379-07aa5e5b0739?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1884&q=10");
        photos.add("https://images.unsplash.com/photo-1579983880984-a202306861b7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2134&q=10");
        photos.add("https://images.unsplash.com/photo-1504439904031-93ded9f93e4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1952&q=10");
        photos.add("https://images.unsplash.com/photo-1512310604669-443f26c35f52?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=10");
        photos.add("https://images.unsplash.com/photo-1456553583670-f4242f36d0fc?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2134&q=10");
        photos.add("https://images.unsplash.com/photo-1599591016875-c9a8b44ed0b2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2132&q=10");
        photos.add("https://images.unsplash.com/photo-1522932467653-e48f79727abf?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1916&q=10");
        photos.add("https://images.unsplash.com/photo-1453856908826-6bbeda0f8490?ixlib=rb-1.2.1&auto=format&fit=crop&w=2133&q=10");
        photos.add("https://images.unsplash.com/photo-1519501025264-65ba15a82390?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1900&q=10");
        photos.add("https://images.unsplash.com/photo-1536685712909-6ac3633e0812?ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=10");
        photos.add("https://images.unsplash.com/photo-1557291563-8e3f22518a01?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2134&q=10");
        photos.add("https://images.unsplash.com/photo-1487101588220-01e039029925?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2134&q=10");
        photos.add("https://images.unsplash.com/photo-1446312299075-18fba3657228?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1816&q=10");
        photos.add("https://images.unsplash.com/photo-1520841852757-e40af9b5bd12?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1901&q=10");
        photos.add("https://images.unsplash.com/photo-1517103962281-4c1a9dd98571?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1900&q=10");
        photos.add("https://images.unsplash.com/photo-1518131945814-56102d3539a1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=10");
        photos.add("https://images.unsplash.com/photo-1519422756681-61778fb58a7b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2134&q=10");
        photos.add("https://images.unsplash.com/photo-1497366754035-f200968a6e72?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=10");
        photos.add("https://images.unsplash.com/photo-1504297050568-910d24c426d3?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2134&q=10");
        photos.add("https://images.unsplash.com/photo-1507679799987-c73779587ccf?ixlib=rb-1.2.1&auto=format&fit=crop&w=1951&q=10");
        photos.add("https://images.unsplash.com/photo-1449247709967-d4461a6a6103?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1951&q=10");
        photos.add("https://images.unsplash.com/photo-1541533260371-b8fc9b596d84?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2134&q=10");
        photos.add("https://images.unsplash.com/photo-1464029902023-f42eba355bde?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=10");
        photos.add("https://images.unsplash.com/photo-1575318633968-0383e7d07ca0?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2133&q=10");
        photos.add("https://images.unsplash.com/photo-1535957998253-26ae1ef29506?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1952&q=10");
        photos.add("https://images.unsplash.com/photo-1593319634705-4f92f42a7fb6?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=10");
        photos.add("https://images.unsplash.com/photo-1463171379579-3fdfb86d6285?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=10");
        photos.add("https://images.unsplash.com/photo-1487700066891-e2af21f110b1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2134&q=10");
        photos.add("https://images.unsplash.com/photo-1554232456-8727aae0cfa4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=10");
        photos.add("https://images.unsplash.com/photo-1464075208758-5623fb69e13b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1560&q=10");
        photos.add("https://images.unsplash.com/photo-1529440547539-b8500cf6c0c3?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=10");
        photos.add("https://images.unsplash.com/photo-1514591404656-22ae99b3261c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1900&q=10");
        photos.add("https://images.unsplash.com/photo-1504681869696-d977211a5f4c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2081&q=10");
        photos.add("https://images.unsplash.com/photo-1518144591331-17a5dd71c477?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1843&q=10");
        photos.add("https://images.unsplash.com/photo-1528543413928-5447744645a0?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1934&q=10");
        photos.add("https://images.unsplash.com/photo-1515224526905-51c7d77c7bb8?ixlib=rb-1.2.1&auto=format&fit=crop&w=1900&q=10");
        photos.add("https://images.unsplash.com/photo-1537949307532-e64f501381ca?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjExMDk0fQ&auto=format&fit=crop&w=2134&q=10");
        photos.add("https://images.unsplash.com/photo-1514760852-53228f1d766a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=10");
        photos.add("https://images.unsplash.com/photo-1523659833296-38a9c0e185af?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=10");
        photos.add("https://images.unsplash.com/photo-1541617219835-3689726fa8e7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2081&q=10");
        photos.add("https://images.unsplash.com/photo-1521412644187-c49fa049e84d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2134&q=10");
        //
        List<String> category = new ArrayList<>();
        category.add("#casual");
        category.add("#people");
        category.add("#travel");
        category.add("#work");
        category.add("#office");
        category.add("#cars");
        category.add("#food");
        category.add("#road");
        category.add("#trip");
        category.add("#instrument");
        category.add("#simple");
        category.add("#woman");
        category.add("#man");
        category.add("#rutine");
        category.add("#dog");
        category.add("#cat");
        category.add("#car");
        category.add("#film");
        category.add("#sky");
        category.add("#ukulele");
        //
        Random r = new Random();
        for (int i = 1; i <= number; i++) {
            long user = r.nextInt((7 - 1) + 1) + 1;
            int category1 = r.nextInt(((category.size() - 1) - 1) + 1) + 1;
            int category2 = r.nextInt(((category.size() - 1) - 1) + 1) + 1;
            int category3 = r.nextInt(((category.size() - 1) - 1) + 1) + 1;
            int photoRdm = r.nextInt((54 - 0) + 1) + 0;
            //
            PostWriteModel post = new PostWriteModel();
            post.setDescription(helloLipsum(category.get(category1), category.get(category2), category.get(category3)));
            post.setPhotoUrl(photos.get(photoRdm));
            post.setUserId(user);

            addPost.addPostFromDao(post);
        }
        //
        commentAdd.addComment(new CommentWriteModel(lipsum, 1L,1L));
        commentAdd.addComment(new CommentWriteModel(lipsum, 2L,2L));
        commentAdd.addComment(new CommentWriteModel(lipsum, 3L,2L));
        commentAdd.addComment(new CommentWriteModel(lipsum, 4L,1L));
        commentAdd.addComment(new CommentWriteModel(lipsum, 5L,1L));
        commentAdd.addComment(new CommentWriteModel(lipsum, 5L,1L));
    }

    @GetMapping("/all")
    List<PostReadModel> elo(){
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
