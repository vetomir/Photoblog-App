package pl.gregorymartin.b01.core.mapping.model;

import lombok.Getter;

@Getter
public class TagReadModel {
    private String name;
    //todo
    private int numberOfPosts;

    public TagReadModel(final String name) {
        this.name = name;
    }
}
