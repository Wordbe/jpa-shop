package co.wordbe.jpashop.repository.post;

import co.wordbe.jpashop.domain.post.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {

    @EntityGraph(attributePaths = {"comments"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<Post> findWithCommentsById(Long aLong);
}
