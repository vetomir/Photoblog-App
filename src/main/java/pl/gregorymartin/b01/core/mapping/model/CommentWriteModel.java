package pl.gregorymartin.b01.core.mapping.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CommentWriteModel {
    private String content;
    private long userId;
    private long postId = 1;

    public CommentWriteModel(final String content) {
        this.content = content;
    }
}
