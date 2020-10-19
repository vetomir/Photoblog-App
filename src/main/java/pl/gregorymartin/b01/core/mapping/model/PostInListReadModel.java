package pl.gregorymartin.b01.core.mapping.model;

import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.b01.core.model.Tag;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class PostInListReadModel {
    private String description;
    private String photoUrl;
    private String createdOn;
    private List<String> tags;
    private int numberOfComments;
    private int numberOfLikes;
    private String nameOfUser;
    //liked by present user
    private boolean presentUser;


    public PostInListReadModel(final String description, final String photoUrl, final LocalDateTime createdOn, final int numberOfComments, final int numberOfLikes, final boolean presentUser) {
        this.description = description;
        this.photoUrl = photoUrl;
        this.createdOn = getCreatedOnFormatted(createdOn);

/*        this.tags = tags.stream()
                .map(x -> new Tag().getTitle())
                .collect(Collectors.toList());*/

        this.numberOfComments = numberOfComments;
        this.numberOfLikes = numberOfLikes;
        this.presentUser = presentUser;
    }
    public String getCreatedOnFormatted(LocalDateTime date){
        return date.format(DateTimeFormatter.ofPattern("dd LLLL yyyy hh:mm a"));
    }
}
