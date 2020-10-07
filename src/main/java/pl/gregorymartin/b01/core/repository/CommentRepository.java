package pl.gregorymartin.b01.core.repository;

import pl.gregorymartin.b01.core.mapping.dto.PostSingle;
import pl.gregorymartin.b01.core.model.Comment;

import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);
}
