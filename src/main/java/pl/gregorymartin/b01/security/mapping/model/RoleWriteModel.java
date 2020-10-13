package pl.gregorymartin.b01.security.mapping.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public
class RoleWriteModel {
    private String name;

    public RoleWriteModel(final String name) {
        this.name = name;
    }
}
