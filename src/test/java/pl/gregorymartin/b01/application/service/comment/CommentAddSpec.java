package pl.gregorymartin.b01.application.service.comment;

import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.Profile;
import pl.gregorymartin.b01.core.mapping.CommentMapper;
import pl.gregorymartin.b01.core.mapping.model.CommentReadModel;
import pl.gregorymartin.b01.core.mapping.model.CommentWriteModel;
import pl.gregorymartin.b01.core.model.Comment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class CommentAddSpec {
    static String LOREM = "Lorem Ipsum";

    @Test
    void addComment() {
        CommentAdd commentAdd = mock(CommentAdd.class);
        CommentReadModel commentReadModel = mock(CommentReadModel.class);
        commentReadModel.setContent(LOREM);
        System.out.println(commentReadModel.getContent());

        given(commentAdd.addComment(Mockito.any(CommentWriteModel.class))).willReturn(commentReadModel);

        CommentReadModel commentWriteModel = commentAdd.addComment(new CommentWriteModel());

        Assert.assertEquals(LOREM, commentWriteModel.getContent());
    }
}
