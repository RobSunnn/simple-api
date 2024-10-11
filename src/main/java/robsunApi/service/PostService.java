package robsunApi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import robsunApi.domain.entity.PostEntity;
import robsunApi.domain.model.binding.PostBindingModel;

public interface PostService {
    PostEntity createPost(PostBindingModel postBindingModel);

    Page<PostEntity> getAllPosts(Pageable pageable);

    PostEntity getPostById(Long id);
}
