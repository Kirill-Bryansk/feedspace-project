package ru.feedspace.application.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.feedspace.api.dto.PostRequest;
import ru.feedspace.api.dto.PostResponse;
import ru.feedspace.application.service.PostService;

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
}
