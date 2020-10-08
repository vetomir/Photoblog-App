package pl.gregorymartin.b01.core.mapping.dto;



import lombok.RequiredArgsConstructor;
import pl.gregorymartin.b01.core.model.Comment;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CommentDtoMapper {

    public static List<CommentDto> mapToCommentDtos(List<Comment> comments) {
        return comments.stream()
                .map(CommentDtoMapper::mapToCommentDto)
                .collect(Collectors.toList());
    }

    private static CommentDto mapToCommentDto(Comment comment) {

        return CommentDto.builder()
                .content(comment.getContent())
                /*.userName(comment.getUser().getUsername)
                .userAvatarUrl(comment.getUser().getAvatar())*/
                .build();
    }
}
