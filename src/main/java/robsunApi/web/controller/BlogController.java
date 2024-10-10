package robsunApi.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import robsunApi.domain.entity.PostEntity;
import robsunApi.domain.model.binding.PostBindingModel;
import robsunApi.service.PostService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {

    private final PostService postService;

    public BlogController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ModelAndView blogPage(Model model) {
        List<PostEntity> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return new ModelAndView("blog-page");
    }

    @PostMapping("/create-post")
    public String createPost(@ModelAttribute PostBindingModel postBindingModel,
                             @RequestParam("image") MultipartFile imageFile) throws IOException {
        postService.createPost(postBindingModel.getTitle(), postBindingModel.getContent(), imageFile);
        return "redirect:/blog";  // Redirect to the blog page after creating the post
    }
}
