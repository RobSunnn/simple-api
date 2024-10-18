package robsunApi.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import robsunApi.domain.entity.PostEntity;
import robsunApi.domain.model.binding.PostBindingModel;
import robsunApi.service.PostService;
import robsunApi.service.TokenService;

@Controller
@RequestMapping("/blog")
public class BlogController {

    private final PostService postService;
    private final TokenService tokenService;

    public BlogController(PostService postService, TokenService tokenService) {
        this.postService = postService;
        this.tokenService = tokenService;
    }

    @ModelAttribute
    public void attributes(Model model) {
        model.addAttribute("postBindingModel", new PostBindingModel());
    }

    @PostMapping("/create-post")
    public ResponseEntity<String> createPost(@RequestHeader("x-api-token") String token, @ModelAttribute PostBindingModel postBindingModel) {
        if (tokenService.isValidToken(token)) {
            boolean isCreated = postService.createPost(postBindingModel);

            if (isCreated) {
                return ResponseEntity.ok("Post created successfully!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create post. Please check your inputs.");
            }

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }


    @GetMapping
    public String getAllPosts(
            Model model,
            @PageableDefault(size = 6) Pageable pageable
    ) {
        Page<PostEntity> page = postService.getAllApprovedPosts(pageable);
        model.addAttribute("posts", page.getContent());
        model.addAttribute("totalElements", page.getTotalElements());
        model.addAttribute("currentPage", page.getNumber());
        model.addAttribute("totalPages", page.getTotalPages());
        return "blog-page";
    }

    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        PostEntity post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "post-details";  // Name of the Thymeleaf template for post details
    }
}
