package pl.gregorymartin.b01.core.mapping;



import lombok.RequiredArgsConstructor;
import pl.gregorymartin.b01.core.mapping.model.CommentWriteModel;
import pl.gregorymartin.b01.core.mapping.model.CommentReadModel;
import pl.gregorymartin.b01.core.model.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CommentMapper {

    public static List<Comment> mapCommentWriteModelsToEntities(List<CommentWriteModel> commentsDao) {
        return commentsDao.stream()
                .map(CommentMapper::mapCommentWriteModelToEntity)
                .collect(Collectors.toList());
    }

    public static Comment mapCommentWriteModelToEntity(CommentWriteModel commentWriteModel) {
        Comment mappedComment = new Comment();
        mappedComment.setContent(commentWriteModel.getContent());

        return mappedComment;
    }

    public static List<CommentReadModel> mapToCommentReadModels(List<Comment> comments) {
        if(comments != null){
            return comments.stream()
                    .map(CommentMapper::mapToCommentReadModel)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public static CommentReadModel mapToCommentReadModel(Comment comment) {

        return CommentReadModel.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdOn(comment.formatCreatedOn())
                /*.userName(comment.getUser().getUsername)
                .userAvatarUrl(comment.getUser().getAvatar())*/
                .build();
    }
}
