package pl.gregorymartin.b01.core.mapping.dao;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentSave {
    private String content;
    private long userId;
    private long postId;

    public CommentSave(final String content) {
        this.content = content;
    }
}
