package pl.gregorymartin.b01.core.mapping.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public
class PostWriteModel {
    @NotBlank(message = "URL Cannot be Blank")
    @URL(message = "This is not URL")
    private String photoUrl;
    @NotBlank(message = "Description Cannot be Blank")
    @Pattern(regexp="^[A-Za-z0-9]*$",message = "Forbidden symbols in Description Field")
    private String description;
    private List<String> tags;
    private long userId;

    public PostWriteModel(@NotBlank(message = "URL Cannot be Blank") @URL(message = "This is not URL") final String photoUrl, @NotBlank(message = "Description Cannot be Blank") @Pattern(regexp = "^[A-Za-z0-9]*$", message = "Forbidden symbols in Description Field") final String description) {
        this.photoUrl = photoUrl;
        this.description = description;
    }
}
