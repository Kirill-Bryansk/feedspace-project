package ru.feedspace.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.feedspace.api.dto.PostRequest;
import ru.feedspace.api.dto.PostResponse;
import ru.feedspace.application.mapper.PostMapper;
import ru.feedspace.domain.model.Post;
import ru.feedspace.domain.repository.PostRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private  final PostMapper postMapper;

    @Override
    public PostResponse createPost(PostRequest request) {
        log.info("Создание нового поста: title={}, author={}",
                request.getTitle(), request.getAuthorName());

        Post post = postMapper.toEntity(request);
        log.debug("Создана сущность Post: {}", post);

        Post savedPost = postRepository.save(post);
        log.info("Пост сохранен в БД с ID: {}", savedPost.getId());

        PostResponse response = postMapper.toResponse(savedPost);
        log.debug("Создан PostResponse: {}", response);

        return response;
    }

    @Override
    public List<PostResponse> getAllPosts() {
        log.info("Получение всех постов из БД");
        List<Post> posts = postRepository.findAll();
        log.debug("Найдено {} постов", posts.size());
        return posts.stream()
                .map(postMapper::toResponse)
                .toList();
    }

    @Override
    public PostResponse likePost(Long postId) {
        return null;
    }

    @Override
    public PostResponse unlikePost(Long postId) {
        return null;
    }
}
