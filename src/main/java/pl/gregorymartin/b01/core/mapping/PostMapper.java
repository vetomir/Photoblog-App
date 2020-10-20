package pl.gregorymartin.b01.core.mapping;



import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import pl.gregorymartin.b01.core.mapping.model.PostWriteModel;
import pl.gregorymartin.b01.core.mapping.model.PostReadModel;
import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.core.model.Tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PostMapper {
    //create
    public static List<Post> mapPostWriteModelsToEntity(List<PostWriteModel> postsDao) {
        return postsDao.stream()
                .map(PostMapper::mapPostWriteModelsToEntity)
                .collect(Collectors.toList());
    }

    public static Post mapPostWriteModelsToEntity(PostWriteModel postWriteModel) {
        Post mappedPost = new Post();
        mappedPost.setDescription(postWriteModel.getDescription());
        mappedPost.setPhotoUrl(postWriteModel.getPhotoUrl());

        postWriteModel.setTags(mapHashTagsFromContentToTagEntities(postWriteModel.getDescription()));
        mappedPost.setTags(
                postWriteModel.getTags().stream()
                        .filter(x -> !x.isBlank())
                        .distinct()
                        .map(Tag::new)
                        .collect(Collectors.toList())
        );

        return mappedPost;
    }

    private static List<String> mapHashTagsFromContentToTagEntities(String content) {
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

    public static List<PostReadModel> mapPostEntityToPostReadModel(List<Post> posts) {
        return posts.stream()
                .map(PostMapper::mapPostEntityToPostReadModel)
                .collect(Collectors.toList());
    }

    public static PostReadModel mapPostEntityToPostReadModel(Post post) {
        return PostReadModel.builder()
                .id(post.getId())
                .description(post.getDescription())
                .photoUrl(post.getPhotoUrl())
                .createdOn(post.formatCreatedOn())
                .userName(post.getUser().getName())
                .userAvatar(post.getUser().getAvatar())
                .numberOfComments(post.getNumberOfComments())
                .numberOfLikes(post.getNumberOfLikes())
                .build();
    }
    public static PostReadModel mapPostEntityToSinglePostReadModel(Post post) {
        return PostReadModel.builder()
                .id(post.getId())
                .description(post.getDescription())
                .photoUrl(post.getPhotoUrl())
                .tags(post.getTags().stream()
                        .map(Tag::getTitle)
                        .collect(Collectors.toList()))
                .createdOn(post.formatCreatedOn())
                .commentReadModels(Lists.reverse(
                        CommentMapper.mapEntityToCommentReadModel(post.getComments())))
                .userName(post.getUser().getName())
                .userAvatar(post.getUser().getAvatar())
                .numberOfComments(post.getNumberOfComments())
                .numberOfLikes(post.getNumberOfLikes())
                .build();
    }
}
