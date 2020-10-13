package pl.gregorymartin.b01.core.mapping;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.gregorymartin.b01.TestConfiguration;
import pl.gregorymartin.b01.core.mapping.model.CommentReadModel;
import pl.gregorymartin.b01.core.mapping.model.CommentWriteModel;
import pl.gregorymartin.b01.core.model.Comment;
import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.core.repository.CommentRepository;
import pl.gregorymartin.b01.security.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class CommentMapperSpec {

    @Test
    void should_mapCommentWriteModelToEntity_andReturnsGivenContent() {
        //given
        CommentWriteModel commentWriteModel = new CommentWriteModel();
        commentWriteModel.setContent("Lorem");

        //when
        Comment comment = CommentMapper.mapCommentWriteModelToEntity(commentWriteModel);

        //then
        Assert.assertEquals("Lorem", comment.getContent());
    }

    @Test
    void should_mapCommentWriteModelToEntity_forList_andReturnsGivenContents() {
        //given
        List<CommentWriteModel> list = new ArrayList<>();

        CommentWriteModel item1 = new CommentWriteModel();
        item1.setContent("Lorem1");
        list.add(item1);
        CommentWriteModel item2 = new CommentWriteModel();
        item2.setContent("Lorem2");
        list.add(item2);


        //when
        List<Comment> commentList = CommentMapper.mapCommentWriteModelToEntity(list);

        //then
        Assert.assertEquals("Lorem1", commentList.get(0).getContent());
        Assert.assertEquals("Lorem2", commentList.get(1).getContent());
        Assert.assertEquals(2, commentList.size());
    }

    @Test
    void should_mapToCommentReadModel_andReturnsGivenEntity() {
        //given
        CommentRepository repositoryMock = mock(CommentRepository.class);
        User user = new User();
        user.setName("wewrdtfyu");
        user.setEmail("sadfsgdh@DASGF.PL");

        Comment comment = new Comment("Lorem");
        comment.setId(1);
        comment.setPost(null);
        comment.setUser(user);
        comment.setCreatedOn(LocalDateTime.now());
        given(repositoryMock.save(comment)).willReturn(comment);

        //when
        Comment savedComment = repositoryMock.save(comment);
        CommentReadModel commentReadModel = CommentMapper.mapEntityToCommentReadModel(savedComment);

        //then
        Assert.assertEquals(comment.getContent(), commentReadModel.getContent());
        Assert.assertEquals(comment.getUser().getName(), commentReadModel.getUserName());

    }
}
