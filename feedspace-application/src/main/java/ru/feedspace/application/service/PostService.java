package ru.feedspace.application.service;

import ru.feedspace.api.dto.PostRequest;
import ru.feedspace.api.dto.PostResponse;

import java.util.List;

public interface PostService {
    PostResponse createPost(PostRequest request);
    List<PostResponse> getAllPosts();
    PostResponse likePost(Long postId);
    PostResponse unlikePost(Long postId);
}
