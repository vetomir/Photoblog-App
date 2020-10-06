package pl.gregorymartin.b01.core.mapping.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
public class PostDto {
    private String description;
    private String photoUrl;
    private String createdOn;
    private List<String> tags;
    private Long userId;
    private int numberOfComments;
    private int numberOfLikes;

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public PostDto(final String description, final String photoUrl, final LocalDateTime createdOn) {
        this.description = description;
        this.photoUrl = photoUrl;
        this.createdOn = getCreatedOnFormatted(createdOn);
    }

    public String getCreatedOnFormatted(LocalDateTime date){
        return date.format(DateTimeFormatter.ofPattern("dd LLLL yyyy hh:mm"));
    }
}