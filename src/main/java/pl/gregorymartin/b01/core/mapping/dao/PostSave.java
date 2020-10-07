package pl.gregorymartin.b01.core.mapping.dao;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public
class PostSave {
    private String photoUrl;
    private String description;
    private List<String> tags;
    private long userId;

    public PostSave(final String photoUrl, final String description) {
        this.photoUrl = photoUrl;
        this.description = description;
    }
}
