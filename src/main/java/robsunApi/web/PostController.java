//package robsunApi.web;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import robsunApi.domain.entity.PostEntity;
//import robsunApi.service.PostService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/posts")
//public class PostController {
//
//    private final PostService postService;
//
//    @Autowired
//    public PostController(PostService postService) {
//        this.postService = postService;
//    }
//
//    @GetMapping
//    public List<PostEntity> getAllPosts() {
//        return postService.getAllPosts();
//    }
//
//    @PostMapping
//    public ResponseEntity<PostEntity> createPost(@RequestBody PostEntity post) {
//        PostEntity createdPost = postService.createPost(post);
//        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
//    }
//}
