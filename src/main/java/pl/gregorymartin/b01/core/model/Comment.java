package pl.gregorymartin.b01.core.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Entity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "comments")
public class Comment extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;

/*    @JsonBackReference
    @ManyToOne//(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userId", updatable = false)
    private User user;*/

    @JsonBackReference
    @ManyToOne//(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "postId", updatable = false)
    private Post post;

    public Comment(String content) {
        this.content = content;
    }
}
