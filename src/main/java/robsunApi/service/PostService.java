package robsunApi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import robsunApi.domain.entity.PostEntity;
import robsunApi.repository.PostRepository;

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

    public PostEntity createPost(PostEntity post) {
        return postRepository.save(post);
    }

}
