package co.wordbe.jpashop.api;

import co.wordbe.jpashop.domain.post.Post;
import co.wordbe.jpashop.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostApiController {
    private final PostService postService;

    @PostMapping("/api/v1/posts")
    public List<Post> savePosts() {
        return postService.savePosts();
    }
}
