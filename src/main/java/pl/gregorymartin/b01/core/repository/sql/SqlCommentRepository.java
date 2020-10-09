package pl.gregorymartin.b01.core.repository.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gregorymartin.b01.core.model.Comment;
import pl.gregorymartin.b01.core.repository.CommentRepository;

@Repository
public
interface SqlCommentRepository extends CommentRepository, JpaRepository<Comment, Long> {
}
