package pl.gregorymartin.b01.core.mapping;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pl.gregorymartin.b01.core.mapping.model.PostReadModel;
import pl.gregorymartin.b01.core.mapping.model.PostWriteModel;
import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.core.model.Tag;
import pl.gregorymartin.b01.security.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class PostMapperSpec {

    @Test
    void should_mapPostWriteModelsToEntity_andReturnsGivenContent_andGeneratedTags() {
        //given
        PostWriteModel postWriteModel = new PostWriteModel("test.pl/test.jpg","ugabuga #Test #TestAnother #Test");

        //when
        Post post = PostMapper.mapPostWriteModelsToEntity(postWriteModel);

        //then
        Assert.assertEquals("ugabuga #Test #TestAnother #Test", post.getDescription());
        Assert.assertEquals("test.pl/test.jpg", post.getPhotoUrl());
        Assert.assertEquals("#Test", post.getTags().get(0).getTitle());
        Assert.assertEquals("#TestAnother", post.getTags().get(1).getTitle());
        Assert.assertEquals(2, post.getTags().size());

    }

    @Test
    void should_mapPostWriteModelsToEntity_forList_andReturnsGivenContents() {
        //given
        List<PostWriteModel> list = new ArrayList<>();

        list.add(new PostWriteModel("test.pl/test.jpg","ugabuga #Test #TestAnother #Test"));
        list.add(new PostWriteModel("test.pl/test1.jpg","ugabuga #Test1 #TestAnother1 #Test1"));


        //when
        List<Post> postList = PostMapper.mapPostWriteModelsToEntity(list);

        //then
        Assert.assertEquals("ugabuga #Test #TestAnother #Test", postList.get(0).getDescription());
        Assert.assertEquals("ugabuga #Test1 #TestAnother1 #Test1", postList.get(1).getDescription());
        Assert.assertEquals(2, postList.size());
    }

    @Test
    void should_mapPostEntityToPostReadModel_andReturnsGivenEntity() {
        //given
        User user = new User();
        user.setName("ugabuga@test.com");
        //and
        Post post = new Post("Post","test.pl/test.jpg");
        post.setUser(user);
        post.setTags(List.of(new Tag("test")));
        post.setCreatedOn(LocalDateTime.now());
        //when
        PostReadModel postReadModel = PostMapper.mapPostEntityToPostReadModel(post);
        //then
        Assert.assertEquals(post.getDescription(), postReadModel.getDescription());
        Assert.assertEquals(post.getUser().getName(), postReadModel.getUserName());
        Assert.assertEquals(post.getTags().get(0).getTitle(), postReadModel.getTags().get(0));

    }
}
