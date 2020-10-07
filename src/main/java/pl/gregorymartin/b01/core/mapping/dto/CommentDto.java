package pl.gregorymartin.b01.core.mapping.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
public class CommentDto {
    private String content;
    private String userName;
    private String userAvatarUrl;

    private String createdOn;
    //commented by present user
    private boolean presentUser;


    public CommentDto(final String content, final String userName, String userAvatarUrl, final LocalDateTime createdOn) {
        this.content = content;
        this.userName = userName;
        this.userAvatarUrl = userAvatarUrl;
        this.createdOn = getCreatedOnFormatted(createdOn);
    }

    public String getCreatedOnFormatted(LocalDateTime date){
        return date.format(DateTimeFormatter.ofPattern("dd LLLL yyyy hh:mm"));
    }
}
