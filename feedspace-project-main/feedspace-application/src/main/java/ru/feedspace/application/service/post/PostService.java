package ru.feedspace.application.service.post;

import ru.feedspace.api.dto.post.PostRequest;
import ru.feedspace.api.dto.post.PostResponse;

import java.util.List;

public interface PostService {
    PostResponse createPost(PostRequest request);
    List<PostResponse> getAllPosts();
    PostResponse likePost(Long postId, Long userId);  // userId пока заглушка
    PostResponse unlikePost(Long postId, Long userId);
}
