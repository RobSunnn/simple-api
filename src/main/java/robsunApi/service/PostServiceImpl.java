package robsunApi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import robsunApi.domain.entity.ImageEntity;
import robsunApi.domain.entity.PostEntity;
import robsunApi.domain.model.binding.PostBindingModel;
import robsunApi.repository.ImageRepository;
import robsunApi.repository.PostRepository;

import java.io.IOException;

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
    public PostEntity createPost(PostBindingModel postBindingModel) {
        PostEntity post = new PostEntity();
        post.setTitle(postBindingModel.getTitle());
        post.setContent(postBindingModel.getContent());

        MultipartFile file = postBindingModel.getFile();
        if (file != null && !file.isEmpty()) {
            try {
                ImageEntity image = new ImageEntity();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setSize(file.getSize());
                image.setData(file.getBytes());
                image.setPost(post);
                imageRepository.save(image);

                post.setImage(image);
            } catch (IOException e) {
                throw new RuntimeException("Failed to store file " + file.getOriginalFilename(), e);
            }
        }

        return postRepository.save(post);
    }

    @Override
    public Page<PostEntity> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public PostEntity getPostById(Long id) {
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        // Force loading of the image data to avoid lazy initialization issues
        if (post.getImage() != null) {
            post.getImage().getData();  // Trigger loading of the image data
        }

        return post;
    }
}
