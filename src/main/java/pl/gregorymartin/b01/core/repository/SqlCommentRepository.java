package pl.gregorymartin.b01.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gregorymartin.b01.core.model.Comment;

@Repository
public
interface SqlCommentRepository extends CommentRepository, JpaRepository<Comment, Long> {
}
