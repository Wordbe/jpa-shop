package co.wordbe.jpashop.repository.post;

import co.wordbe.jpashop.domain.post.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
