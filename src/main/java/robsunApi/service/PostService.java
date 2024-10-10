package robsunApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import robsunApi.domain.entity.ImageEntity;
import robsunApi.domain.entity.PostEntity;
import robsunApi.repository.PostRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostEntity> getAllPosts() {
        return postRepository.findAll();
    }


    public void createPost(String title, String content, MultipartFile imageFile) throws IOException {
        // Check if the file is empty
        if (imageFile.isEmpty()) {
            throw new IOException("Cannot upload empty file");
        }

        // Create a directory for images if it doesn't exist
        String uploadDir = "src/main/resources/static/images/";
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs(); // Create the directory if it doesn't exist
        }

        // Save the image to the defined directory
        String imagePath = uploadDir + imageFile.getOriginalFilename();
        File imageFilePath = new File(imagePath);

        // Use OutputStream to save the file
        try (InputStream inputStream = imageFile.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(imageFilePath)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new IOException("Failed to save the file: " + e.getMessage(), e);
        }

        // Create the ImageEntity and set the file path
        ImageEntity imageEntity = new ImageEntity("/images/" + imageFile.getOriginalFilename());

        // Save the post with the image
        PostEntity postEntity = new PostEntity(title, content, imageEntity);
        postRepository.save(postEntity);
    }

}
