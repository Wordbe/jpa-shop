package co.wordbe.jpashop.domain.post;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Comment {
    @Id @GeneratedValue
    private Long id;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "post_id", insertable = false, updatable = false)
    private Long postId;

    public Comment(String content) {
        this.content = content;
    }
}
