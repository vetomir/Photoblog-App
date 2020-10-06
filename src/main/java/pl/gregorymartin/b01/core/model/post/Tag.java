package pl.gregorymartin.b01.core.model.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import pl.gregorymartin.b01.core.Audit;
import pl.gregorymartin.b01.core.model.post.Post;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tags")
public class Tag extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /*@Pattern(regexp="^[A-Za-z0-9]*$",message = "Forbidden symbols in Category Field")*/
    @NotEmpty
    private String title;

    private int numberOfPosts;


    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    private List<Post> posts;

    public Tag(String title) {
        numberOfPosts = 1;
        this.title = title;
    }


}
