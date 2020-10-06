package pl.gregorymartin.b01.core.model.post;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.gregorymartin.b01.core.Audit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "posts")
public class Post extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String photoUrl;
    @NotBlank
    private String description;

    private int numberOfLikes;
    private int numberOfComments;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "posts_tags",
            joinColumns = @JoinColumn(
                    name = "posts_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "tags_id", referencedColumnName = "id"))
    private List<Tag> tags = new ArrayList<>();

    @OneToMany//(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "postId", insertable = false)
    private List<Comment> comments;

/*    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "posts_rates",
            joinColumns = @JoinColumn(
                    name = "posts_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "rates_id", referencedColumnName = "id"))
    private Set<Rate> rates = new HashSet<>();*/


/*    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userId", updatable = false)
    private User user;*/

    public Post() {
        this.tags.add(new Tag("ugabuga"));
    }

    public Post(String description, String photoUrl) {
        this.description = description;
        this.photoUrl = photoUrl;
    }
}
