package robsunApi.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import robsunApi.domain.entity.PostEntity;
import robsunApi.domain.model.binding.PostBindingModel;
import robsunApi.service.PostService;

@Controller
@RequestMapping("/blog")
public class BlogController {

    private final PostService postService;

    public BlogController(PostService postService) {
        this.postService = postService;
    }

    @ModelAttribute
    public void attributes(Model model) {
        model.addAttribute("postBindingModel", new PostBindingModel());
    }

    @PostMapping("/create-post")
    public String createPost(@ModelAttribute PostBindingModel postBindingModel, RedirectAttributes redirectAttributes) {
        postService.createPost(postBindingModel);
        redirectAttributes.addFlashAttribute("message", "Post created successfully!");
        return "redirect:/blog";
    }

    @GetMapping
    public String getAllPosts(
            Model model,
            @PageableDefault(size = 3) Pageable pageable
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
