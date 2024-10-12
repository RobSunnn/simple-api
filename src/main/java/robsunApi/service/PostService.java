package robsunApi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import robsunApi.domain.entity.PostEntity;
import robsunApi.domain.model.binding.PostBindingModel;
import robsunApi.domain.model.view.PostView;

import java.util.List;

public interface PostService {
    PostEntity createPost(PostBindingModel postBindingModel);

    Page<PostEntity> getAllApprovedPosts(Pageable pageable);

    List<PostView> getAllNotApprovedPosts();

    PostEntity getPostById(Long id);


    void approvePost(Long id);
}
