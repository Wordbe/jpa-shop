package co.wordbe.jpashop.domain.post;

import co.wordbe.jpashop.repository.post.CommentRepository;
import co.wordbe.jpashop.repository.post.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Rollback(false)
class PostTest {
    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    EntityManager em;

    @Test
    void 게시글과댓글_외래키확인하기() {
        Post post = new Post();
        post.setTitle("백종원의 김치찌개");
        post.setContent("1:3의 비율로");

        Comment comment1 = new Comment();
        comment1.setContent("맛있어요.");
        comment1.setPost(post);

        Comment comment2 = new Comment();
        comment2.setContent("뜨거워요.");
        comment2.setPost(post);

        Post savedPost = postRepository.save(post);
        Comment savedComment1 = commentRepository.save(comment1);
        Comment savedComment2 = commentRepository.save(comment2);

        em.flush(); em.clear();

        Comment foundComment1 = commentRepository.findById(savedComment1.getId()).get();
        Comment foundComment2 = commentRepository.findById(savedComment2.getId()).get();

        assertThat(foundComment1.getPostId()).isEqualTo(savedPost.getId());
        assertThat(foundComment2.getPostId()).isEqualTo(savedPost.getId());
    }

    @Test
    void 연관관계주인_반대편에만_값설정하면_외래키는_null이다() {
        Comment comment1 = new Comment("맛있어요.");
        Comment comment2 = new Comment("뜨거워요.");

        Post post = new Post("백종원의 김치찌개", "1:3의 비율로");
        post.getComments().add(comment1);
        post.getComments().add(comment2);

        postRepository.save(post);
        Comment savedComment1 = commentRepository.save(comment1);
        Comment savedComment2 = commentRepository.save(comment2);

        em.flush(); em.clear();

        Comment foundComment1 = commentRepository.findById(savedComment1.getId()).get();
        Comment foundComment2 = commentRepository.findById(savedComment2.getId()).get();

        assertThat(foundComment1.getPostId()).isNull();
        assertThat(foundComment2.getPostId()).isNull();
    }

    @Test
    void 연관관계편의메소드사용하면_외래키가잘등록된다() {
        Comment comment1 = new Comment("맛있어요.");
        Comment comment2 = new Comment("뜨거워요.");

        Post post = new Post("백종원의 김치찌개", "1:3의 비율로");
        post.changeComment(comment1);
        post.changeComment(comment2);

        Post savedPost = postRepository.save(post);
        Comment savedComment1 = commentRepository.save(comment1);
        Comment savedComment2 = commentRepository.save(comment2);

        em.flush(); em.clear();

        Comment foundComment1 = commentRepository.findById(savedComment1.getId()).get();
        Comment foundComment2 = commentRepository.findById(savedComment2.getId()).get();

        assertThat(foundComment1.getPostId()).isEqualTo(savedPost.getId());
        assertThat(foundComment2.getPostId()).isEqualTo(savedPost.getId());
    }
}