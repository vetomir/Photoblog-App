package pl.gregorymartin.b01.security.mapping.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public
class UserReadModel {
    private Long id;
    private String name;
    //email is username
    private String email;
    private String avatar;
    private List<String> roles;
    private String createdOn;
}
