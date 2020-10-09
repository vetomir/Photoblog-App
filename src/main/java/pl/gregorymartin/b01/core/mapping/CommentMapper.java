package pl.gregorymartin.b01.core.mapping;



import lombok.RequiredArgsConstructor;
import pl.gregorymartin.b01.core.mapping.model.CommentWriteModel;
import pl.gregorymartin.b01.core.mapping.model.CommentReadModel;
import pl.gregorymartin.b01.core.model.Comment;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CommentMapper {

    public static List<Comment> mapDaoToEntity(List<CommentWriteModel> commentsDao) {
        return commentsDao.stream()
                .map(CommentMapper::mapDaoToEntity)
                .collect(Collectors.toList());
    }

    public static Comment mapDaoToEntity(CommentWriteModel commentWriteModel) {
        Comment mappedComment = new Comment();
        mappedComment.setContent(commentWriteModel.getContent());

        return mappedComment;
    }

    public static List<CommentReadModel> mapToCommentDtos(List<Comment> comments) {
        return comments.stream()
                .map(CommentMapper::mapToCommentDto)
                .collect(Collectors.toList());
    }

    private static CommentReadModel mapToCommentDto(Comment comment) {

        return CommentReadModel.builder()
                .content(comment.getContent())
                .createdOn(comment.formatCreatedOn())
                /*.userName(comment.getUser().getUsername)
                .userAvatarUrl(comment.getUser().getAvatar())*/
                .build();
    }
}
