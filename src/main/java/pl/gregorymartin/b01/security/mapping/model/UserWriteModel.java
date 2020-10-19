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
    private String name;
    //email is username
    private String email;
    private String password;
    private String password2;

    public UserWriteModel(final String name, final String email, final String password, final String password2) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.password2 = password2;
    }
}
