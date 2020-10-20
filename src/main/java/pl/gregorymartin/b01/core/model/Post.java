package pl.gregorymartin.b01.core.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.b01.security.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "posts_tags",
            joinColumns = @JoinColumn(
                    name = "postId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "tagId", referencedColumnName = "id"))
    private List<Tag> tags = new ArrayList<>();



    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "posts_rates",
            joinColumns = @JoinColumn(
                    name = "postId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "categoryId", referencedColumnName = "id"))
    private Set<Rate> rates = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId",  insertable = false)
    private List<Comment> comments;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", updatable = false)
    private User user;

    public Post() {
        this.tags.add(new Tag("test"));
    }

    public Post(String description, String photoUrl) {
        this.description = description;
        this.photoUrl = photoUrl;
    }
}
