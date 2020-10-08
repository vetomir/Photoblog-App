package pl.gregorymartin.b01.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.gregorymartin.b01.core.mapping.dto.CommentDto;
import pl.gregorymartin.b01.core.mapping.dto.PostSingle;
import pl.gregorymartin.b01.core.model.Comment;

import java.awt.print.Pageable;
import java.util.Optional;

@Repository
public
interface SqlCommentRepository extends CommentRepository, JpaRepository<Comment, Long> {
}
