package pl.gregorymartin.b01.core.mapping.dto;



import lombok.RequiredArgsConstructor;
import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.core.model.Tag;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PostDtoMapper {

    public static List<PostSingle> mapToPostDtos(List<Post> posts) {
        return posts.stream()
                .map(PostDtoMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    public static PostSingle mapToPostDto(Post post) {

        return PostSingle.builder()
                .description(post.getDescription())
                .photoUrl(post.getPhotoUrl())
                .tags(post.getTags().stream()
                        .map(Tag::getTitle)
                        .collect(Collectors.toList()))
                .createdOn(post.getCreatedOnFormatted())
                .commentDtos(CommentDtoMapper.mapToCommentDtos(post.getComments()))
                /*.userName(post.getUser().getUsername)*/
                /*.userAvatar(post.getUser().getAvatar())*/
                .build();
    }
}
