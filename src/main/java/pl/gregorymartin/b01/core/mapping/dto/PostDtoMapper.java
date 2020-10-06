package pl.gregorymartin.b01.core.mapping.dto;/*
package pl.gregorymartin.photoblogapi.mapping.dto;

import lombok.RequiredArgsConstructor;
import pl.gregorymartin.photoblogapi.core.post.Post;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PostDtoMapper {

    public static List<PostDto> mapToPostDtos(List<Post> posts) {
        return posts.stream()
                .map(post -> mapToPostDto(post))
                .collect(Collectors.toList());
    }

    private static PostDto mapToPostDto(Post post) {

        return PostDto.builder()
                .content(post.getContent())
                .getPhotoUrl(post.getPhotoUrl())
                .categories(post.getCategories())
                .userId(post.getUser().getId())
                .build();
    }
}
*/
