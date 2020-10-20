package pl.gregorymartin.b01.core.mapping.model;

import lombok.Getter;

@Getter
public class TagReadModel {
    private String name;
    private int numberOfPosts;

    public TagReadModel(final String name, final int numberOfPosts) {
        this.name = name;
        this.numberOfPosts = numberOfPosts;
    }
}
