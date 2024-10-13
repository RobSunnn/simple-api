package robsunApi.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import robsunApi.domain.model.view.PostView;
import robsunApi.service.PostService;

import java.util.List;

@Controller
public class AdminController {

    private final PostService postService;

    public AdminController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ADMIN')") // Restrict access to users with the ADMIN role
    @GetMapping("/admin/unapproved-posts")
    public String showUnapprovedPosts(Model model) {
        List<PostView> unapprovedPosts = postService.getAllNotApprovedPosts();
        model.addAttribute("unapprovedPosts", unapprovedPosts);
        return "unapproved-posts"; // Thymeleaf template name
    }

    @PreAuthorize("hasRole('ADMIN')") // Restrict access to users with the ADMIN role
    @PostMapping("/admin/approve-post/{id}")
    public String approvePost(@PathVariable Long id) {
        postService.approvePost(id);
        return "redirect:/admin/unapproved-posts"; // Redirect back to the unapproved posts page
    }
}
