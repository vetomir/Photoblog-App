package pl.gregorymartin.b01.security.mapping.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
public
class RoleReadModel {
    private long id;
    private String name;
    private List<String> users;
}
