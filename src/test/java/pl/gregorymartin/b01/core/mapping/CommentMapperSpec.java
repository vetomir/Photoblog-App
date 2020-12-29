package pl.gregorymartin.b01.core.mapping;

import org.junit.jupiter.api.Test;
import pl.gregorymartin.b01.core.mapping.model.CommentReadModel;
import pl.gregorymartin.b01.core.mapping.model.CommentWriteModel;
import pl.gregorymartin.b01.core.model.Comment;
import pl.gregorymartin.b01.security.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommentMapperSpec {

    @Test
    void should_mapCommentWriteModelToEntity_andReturnsGivenContent() {
        //given
        CommentWriteModel commentWriteModel = new CommentWriteModel();
        commentWriteModel.setContent("Lorem");
        //when
        Comment comment = CommentMapper.mapCommentWriteModelToEntity(commentWriteModel);
        //then
        assertEquals("Lorem", comment.getContent());
    }

    @Test
    void should_mapCommentWriteModelToEntity_forList_andReturnsGivenContents() {
        //given
        List<CommentWriteModel> list = new ArrayList<>();
        //and
        CommentWriteModel item1 = new CommentWriteModel();
        item1.setContent("Lorem1");
        list.add(item1);
        //and
        CommentWriteModel item2 = new CommentWriteModel();
        item2.setContent("Lorem2");
        list.add(item2);
        //when
        List<Comment> commentList = CommentMapper.mapCommentWriteModelToEntity(list);
        //then
        assertEquals("Lorem1", commentList.get(0).getContent());
        assertEquals("Lorem2", commentList.get(1).getContent());
        assertEquals(2, commentList.size());
    }

    @Test
    void should_mapToCommentReadModel_andReturnsGivenEntity() {
        //given
        User user = new User();
        user.setName("sadfsgdh@DASGF.PL");
        //and
        Comment comment = new Comment("Lorem");
        comment.setId(1);
        comment.setPost(null);
        comment.setUser(user);
        comment.setCreatedOn(LocalDateTime.now());
        //when
        CommentReadModel commentReadModel = CommentMapper.mapEntityToCommentReadModel(comment);
        //then
        assertEquals(comment.getContent(), commentReadModel.getContent());
        assertEquals(comment.getUser().getName(), commentReadModel.getUserName());
    }
}
