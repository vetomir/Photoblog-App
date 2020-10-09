package pl.gregorymartin.b01.core.mapping.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@Builder
public class CommentReadModel {
    private String content;
    private String userName;
    private String userAvatarUrl;

    private String createdOn;
    //commented by present user
    private boolean presentUser;



}
