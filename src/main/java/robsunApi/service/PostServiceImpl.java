package robsunApi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import robsunApi.domain.entity.ImageEntity;
import robsunApi.domain.entity.PostEntity;
import robsunApi.domain.model.binding.PostBindingModel;
import robsunApi.domain.model.view.PostView;
import robsunApi.repository.ImageRepository;
import robsunApi.repository.PostRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ImageRepository imageRepository;

    public PostServiceImpl(PostRepository postRepository, ImageRepository imageRepository) {
        this.postRepository = postRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public boolean createPost(PostBindingModel postBindingModel) {
        PostEntity post = new PostEntity();
        post.setTitle(postBindingModel.getTitle());
        post.setContent(postBindingModel.getContent());
        post.setApproved(false);

        MultipartFile file = postBindingModel.getFile();

        // Validate file
        if (file == null || file.isEmpty()) {
            return false; // Invalid file input, return false
        }

        if (file.getSize() > 5 * 1024 * 1024) { // 5MB size limit
            return false; // File size exceeds limit, return false
        }

        try {
            // Create and save image entity
            ImageEntity image = new ImageEntity();
            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setSize(file.getSize());
            image.setData(file.getBytes());
            image.setPost(post);
            imageRepository.save(image);

            post.setImage(image);
        } catch (IOException e) {
            // Log the error if necessary
            return false; // Return false on failure to process image
        }

        postRepository.save(post);
        return true; // Return true if the post is successfully created
    }


    @Override
    public Page<PostEntity> getAllApprovedPosts(Pageable pageable) {
        return postRepository.findByApprovedTrue(pageable);
    }

    @Override
    public List<PostView> getAllNotApprovedPosts() {
        return postRepository
                .findByApprovedFalse()
                .stream()
                .filter(post -> !post.isApproved())
                .map(PostServiceImpl::mapToPostView)
                .collect(Collectors.toList());
    }

    private static PostView mapToPostView(PostEntity post) {
        return new PostView(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getImage() != null ? post.getImage().getId() : null
        );
    }


    @Override
    @Transactional
    public PostEntity getPostById(Long id) {
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        // Force loading of the image data to avoid lazy initialization issues
        if (post.getImage() != null) {
            post.getImage().getData();
        }
        return post;
    }

    @Override
    public void approvePost(Long id) {
        Optional<PostEntity> postById = postRepository.findById(id);
        if (postById.isPresent()) {
            PostEntity postEntity = postById.get();
            postEntity.setApproved(true);

            postRepository.save(postEntity);
        }
    }
}
