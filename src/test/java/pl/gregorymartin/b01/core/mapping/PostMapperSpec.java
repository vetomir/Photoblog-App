package pl.gregorymartin.b01.core.mapping;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pl.gregorymartin.b01.core.mapping.model.CommentReadModel;
import pl.gregorymartin.b01.core.mapping.model.CommentWriteModel;
import pl.gregorymartin.b01.core.mapping.model.PostReadModel;
import pl.gregorymartin.b01.core.mapping.model.PostWriteModel;
import pl.gregorymartin.b01.core.model.Comment;
import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.core.model.Tag;
import pl.gregorymartin.b01.core.repository.CommentRepository;
import pl.gregorymartin.b01.core.repository.PostRepository;
import pl.gregorymartin.b01.security.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

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
        PostRepository repositoryMock = mock(PostRepository.class);
        User user = new User();
        user.setName("ugabuga");
        user.setEmail("ugabuga@test.com");

        Post post = new Post("Post","test.pl/test.jpg");
        post.setUser(user);
        post.setTags(List.of(new Tag("test")));

        post.setCreatedOn(LocalDateTime.now());
        given(repositoryMock.save(post)).willReturn(post);

        //when
        Post savedPost = repositoryMock.save(post);
        PostReadModel postReadModel = PostMapper.mapPostEntityToPostReadModel(savedPost);

        //then
        Assert.assertEquals(post.getDescription(), postReadModel.getDescription());
        Assert.assertEquals(post.getUser().getName(), postReadModel.getUserName());
        Assert.assertEquals(post.getTags().get(0).getTitle(), postReadModel.getTags().get(0));

    }
}
