package pl.gregorymartin.b01.core.model.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gregorymartin.b01.core.model.post.Comment;

@Repository
public
interface SqlCommentRepository extends /*PostRepository,*/ JpaRepository<Comment, Long> {
}
