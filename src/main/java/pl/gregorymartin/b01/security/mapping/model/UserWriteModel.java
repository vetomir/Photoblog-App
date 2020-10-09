package pl.gregorymartin.b01.security.mapping.model;

import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
class UserWriteModel {
    private String name;
    private String username;
    private String password;
    private String password2;
    private String email;
    private String role;
}
