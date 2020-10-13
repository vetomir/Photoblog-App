package pl.gregorymartin.b01.security.mapping.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public
class UserWriteModel {
    //email is username
    private long id;
    private String email;
    private String password;
    private String password2;
    private String role;
}
