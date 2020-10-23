package pl.gregorymartin.b01.core.mapping.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PostReadModel {
    private Long id;
    private String description;
    private String photoUrl;
    private String createdOn;
    private List<String> tags;

    private String userName;
    private String userAvatar;

    private List<CommentReadModel> commentReadModels;

    private int numberOfComments;
    private int numberOfLikes;

    private String patternItem;

    //liked by present user
    private boolean presentUser;


}
