package pl.gregorymartin.b01.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gregorymartin.b01.security.model.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "rates")
public class Rate extends Audit {
    @Id
    private long id;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "rates")
    private Set<Post> posts;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appUserId")
    private User user;

    public Rate(User user) {
        this.user = user;
        this.id = user.getId();
    }
}
