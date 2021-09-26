package co.wordbe.jpashop.domain.post;

import co.wordbe.jpashop.repository.post.CommentRepository;
import co.wordbe.jpashop.repository.post.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

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
        post.addComment(comment1);
        post.addComment(comment2);

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
    void 일대다단방향은_UPDATE_SQL이_실행된다() {
        Comment comment1 = new Comment("맛있어요.");
        Comment comment2 = new Comment("뜨거워요.");

        Post post = new Post("백종원의 김치찌개", "1:3의 비율로");
        post.addComment(comment1);
        post.addComment(comment2);
    }

    @Test
    void saved객체_변경감지_테스트() {
        Post post = new Post("백종원의 김치찌개", "1:3의 비율로");
        Post savedPost = postRepository.save(post); //insert
        savedPost.setTitle("제목 변경"); //update
    }

    @Test
    void updateCollection() {
        Comment comment1 = new Comment("맛있어요.");
        Comment comment2 = new Comment("뜨거워요.");

        Post post = new Post("백종원의 김치찌개", "1:3의 비율로");
        post.addComment(comment1);
        post.addComment(comment2);

        Post savedPost = postRepository.save(post);
        System.out.println("savedPost = " + savedPost);
        em.flush(); em.clear();
        System.out.println("================================================================================");

        Post post1 = postRepository.findWithCommentsById(savedPost.getId()).get();
        System.out.println("post1 = " + post1);
//

        Comment comment3 = new Comment("댓글 3");
        Comment comment4 = new Comment("댓글 4");
        Comment comment5 = new Comment("댓글 5");
        List<Comment> comments = Arrays.asList(comment3, comment4, comment5);
        post1.updateComments(comments);
        System.out.println("post1 = " + post1);
        em.flush(); em.clear();
        System.out.println("================================================================================");

        Post post2 = postRepository.findWithCommentsById(savedPost.getId()).get();
        System.out.println("post2 = " + post2);
    }

    @Test
    void bulk_insert() {
        Post post1 = new Post("백종원의 김치찌개", "1:3의 비율로");
        Post post2 = new Post("백종원의 참치찌개", "1:3의 비율로");
        Post post3 = new Post("백종원의 김치볶음밥", "1:3의 비율로");
        Post post4 = new Post("백종원의 된장찌개", "1:3의 비율로");
        Post post5 = new Post("백종원의 소고기무국", "1:3의 비율로");
        List<Post> posts = Arrays.asList(post1, post2, post3, post4, post5);

        postRepository.saveAll(posts); // GeneratedValue = IDENTITY에서 insert 한 건씩 된다.
    }
}