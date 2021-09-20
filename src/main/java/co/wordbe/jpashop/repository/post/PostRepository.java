package co.wordbe.jpashop.repository.post;

import co.wordbe.jpashop.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
