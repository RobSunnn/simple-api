package robsunApi.web.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import robsunApi.domain.entity.PostEntity;
import robsunApi.service.PostService;

@Controller
@RequestMapping("/images")
public class ImageController {

    private final PostService postService;

    public ImageController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable Long postId) {
        PostEntity post = postService.getPostById(postId);

        if (post.getImage() != null) {
            byte[] imageData = post.getImage().getData();  // Assuming `getData()` retrieves the byte[] from ImageEntity
            ByteArrayResource resource = new ByteArrayResource(imageData);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"image.jpg\"")
                    .contentType(MediaType.IMAGE_JPEG)  // or MediaType.IMAGE_PNG depending on the format
                    .body(resource);
        }

        return ResponseEntity.notFound().build();
    }
}
