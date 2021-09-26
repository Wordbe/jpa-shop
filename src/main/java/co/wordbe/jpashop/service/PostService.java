package co.wordbe.jpashop.service;

import co.wordbe.jpashop.domain.post.Post;
import co.wordbe.jpashop.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public List<Post> savePosts() {
        Post post1 = new Post("백종원의 김치찌개", "1:3의 비율로");
        Post post2 = new Post("백종원의 참치찌개", "1:3의 비율로");
        Post post3 = new Post("백종원의 김치볶음밥", "1:3의 비율로");
        Post post4 = new Post("백종원의 된장찌개", "1:3의 비율로");
        Post post5 = new Post("백종원의 소고기무국", "1:3의 비율로");
        List<Post> posts = Arrays.asList(post1, post2, post3, post4, post5);

        return postRepository.saveAll(posts); // GeneratedValue = IDENTITY에서 insert 한 건씩 된다.
    }
}
