package pl.gregorymartin.b01.security.mapping.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public
class UserWriteModel {
    private long id;
    //email is username
    private String email;
    private String password;
    private String password2;
    private String role;

    public UserWriteModel(final String email, final String password, final String password2) {
        this.email = email;
        this.password = password;
        this.password2 = password2;
    }
}
