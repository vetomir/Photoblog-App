package pl.gregorymartin.b01.security.mapping.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
public
class UserWriteModel {
    private long id;
    @NotBlank(message = "Name field cannot be blank")
    private String name;
    //email is username
    @NotBlank(message = "Email field cannot be blank")
    private String email;
    @NotBlank(message = "Password field cannot be blank")
    private String password;
    @NotBlank(message = "Repeat Password field cannot be blank")
    private String password2;

    public UserWriteModel(final String name, final String email, final String password, final String password2) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.password2 = password2;
    }
}
