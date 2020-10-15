package pl.gregorymartin.b01.core.mapping.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CommentWriteModel {
    private String content;
    private long userId;
    private long postId;


    public CommentWriteModel(final String content, final long userId, final long postId) {
        this.content = content;
        this.userId = userId;
        this.postId = postId;
    }
}
