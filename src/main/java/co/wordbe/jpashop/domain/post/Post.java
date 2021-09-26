package co.wordbe.jpashop.domain.post;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void addComment(Comment comment) {
        if (comment.getPost() != null) {
          comment.getPost().getComments().remove(comment);
        }
        comment.setPost(this);
        this.comments.add(comment);
    }

    public void updateComments(List<Comment> comments) {
        if (this.comments != null) {
            for (Comment beforeComment : this.comments) {
                beforeComment.setPost(null);
            }
        }

        this.comments = comments;
        for (Comment comment : comments) {
            comment.setPost(this);
        }
    }
}

// 일대다 단방향
//@Entity
//public class Post {
//    @Id
//    @GeneratedValue
//    private Long id;
//    private String title;
//    private String content;
//
//    @OneToMany
//    @JoinColumn(name = "post_id") // Comment 테이블의 post_id 를 말한다. (FK)
//    private List<Comment> comments = new ArrayList<>();
//}