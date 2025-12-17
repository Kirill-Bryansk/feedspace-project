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
    public PostResponse likePost(@PathVariable Long id,
                                 @RequestParam(required = false) Long userId) {
        log.debug("PUT: лайк поста ID: {}, пользователь ID: {}", id, userId);

        // TODO: Получать userId из аутентификации
        Long currentUserId = (userId != null) ? userId : 1L;  // Заглушка

        return postService.likePost(id, currentUserId);
    }

    @DeleteMapping("/{id}/like")
    public PostResponse unlikePost(@PathVariable Long id,
                                   @RequestParam(required = false) Long userId) {
        log.debug("DELETE: удаление лайка с поста ID: {}, пользователь ID: {}", id, userId);

        Long currentUserId = (userId != null) ? userId : 1L;  // Заглушка

        return postService.unlikePost(id, currentUserId);
    }
}
