package pl.gregorymartin.b01.core.mapping;



import lombok.RequiredArgsConstructor;
import pl.gregorymartin.b01.core.mapping.model.PostWriteModel;
import pl.gregorymartin.b01.core.mapping.model.PostReadModel;
import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.core.model.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PostMapper {


    //create
    public static List<Post> mapDaoToEntity(List<PostWriteModel> postsDao) {
        return postsDao.stream()
                .map(post -> mapDaoToEntity(post))
                .collect(Collectors.toList());
    }

    public static Post mapDaoToEntity(PostWriteModel postWriteModel) {
        Post mappedPost = new Post();
        mappedPost.setDescription(postWriteModel.getDescription());
        mappedPost.setPhotoUrl(postWriteModel.getPhotoUrl());

        postWriteModel.setTags(tagsFromContent(postWriteModel.getDescription()));
        mappedPost.setTags(
                postWriteModel.getTags().stream()
                        .filter(x -> !x.isBlank())
                        .distinct()
                        .map(Tag::new)
                        .collect(Collectors.toList())
        );

        return mappedPost;
    }

    private static List<String> tagsFromContent(String content) {
        String text = content;
        String[] words = text.split(" ");
        List<String> tags = new ArrayList<String>();

        for ( String word : words) {
            if (word.substring(0, 1).equals("#")) {
                tags.add(word);
            }
        }
        return tags;
    }

    //read

    public static List<PostReadModel> mapToPostDtos(List<Post> posts) {
        return posts.stream()
                .map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    public static PostReadModel mapToPostDto(Post post) {

        return PostReadModel.builder()
                .description(post.getDescription())
                .photoUrl(post.getPhotoUrl())
                .tags(post.getTags().stream()
                        .map(Tag::getTitle)
                        .collect(Collectors.toList()))
                .createdOn(post.formatCreatedOn())
                .commentReadModels(CommentMapper.mapToCommentDtos(post.getComments()))
                /*.userName(post.getUser().getUsername)*/
                /*.userAvatar(post.getUser().getAvatar())*/
                .numberOfComments(post.getNumberOfComments())
                .numberOfLikes(post.getNumberOfLikes())
                .build();
    }
}
