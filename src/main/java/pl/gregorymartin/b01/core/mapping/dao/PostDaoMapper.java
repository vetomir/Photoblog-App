package pl.gregorymartin.b01.core.mapping.dao;


import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.core.model.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostDaoMapper {

    public PostDaoMapper() {
    }

    public static List<Post> mapDaoToEntity(List<PostSave> postsDao) {
        return postsDao.stream()
                .map(post -> mapDaoToEntity(post))
                .collect(Collectors.toList());
    }

    public static Post mapDaoToEntity(PostSave postDao) {
        Post mappedPost = new Post();
        mappedPost.setDescription(postDao.getDescription());
        mappedPost.setPhotoUrl(postDao.getPhotoUrl());

        postDao.setTags(tagsFromContent(postDao.getDescription()));
        mappedPost.setTags(
                postDao.getTags().stream()
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
}
