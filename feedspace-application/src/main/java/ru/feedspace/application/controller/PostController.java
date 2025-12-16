package ru.feedspace.application.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.feedspace.api.dto.post.PostRequest;
import ru.feedspace.api.dto.post.PostResponse;
import ru.feedspace.application.service.post.PostService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse createPost(@RequestBody PostRequest request) {
        log.debug("POST: создание поста. Данные - {}", request);
        return postService.createPost(request);
    }

    @GetMapping
    public List<PostResponse> getAllPosts() {
        log.debug("GET: получение всех постов");
        return postService.getAllPosts();
    }

    @PutMapping("/{id}/like")
    public PostResponse likePost(@PathVariable Long id) {
        log.debug("PUT: лайк поста ID: {}", id);
        // TODO: получить userId из аутентификации, пока используем заглушку
        Long userId = 1L;
        return postService.likePost(id, userId);
    }

    @DeleteMapping("/{id}/like")
    public PostResponse unlikePost(@PathVariable Long id) {
        log.debug("DELETE: удаление лайка с поста ID: {}", id);
        Long userId = 1L;
        return postService.unlikePost(id, userId);
    }
}
