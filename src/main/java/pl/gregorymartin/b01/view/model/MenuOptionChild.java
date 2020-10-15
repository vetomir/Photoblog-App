package pl.gregorymartin.b01.view.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gregorymartin.b01.core.model.Post;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "menu_option_children")
public
class MenuOptionChild {
    @Id
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String source;

    @JsonBackReference
    @ManyToOne//(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "menuOptionId", updatable = false)
    private MenuOption menuOption;
}
