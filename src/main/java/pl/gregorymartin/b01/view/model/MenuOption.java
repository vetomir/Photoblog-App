package pl.gregorymartin.b01.view.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gregorymartin.b01.core.model.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "menu_options")
public
class MenuOption {
    @Id
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String source;

    @OneToMany//(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "menuOptionId",  insertable = false)
    private List<MenuOptionChild> menuOptionChildren;
}
