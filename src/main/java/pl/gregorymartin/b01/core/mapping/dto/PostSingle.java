package pl.gregorymartin.b01.core.mapping.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.b01.core.model.Comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class PostSingle {
    private String description;
    private String photoUrl;
    private String createdOn;
    private List<String> tags;

    private String userName;
    private String userAvatar;

    private List<CommentDto> commentDtos;

    private int numberOfComments;
    private int numberOfLikes;

    //liked by present user
    private boolean presentUser;


}
