package ru.feedspace.application.service.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.feedspace.api.dto.post.PostResponse;
import ru.feedspace.api.dto.post.PostRequest;
import ru.feedspace.api.exceptions.ResourceNotFoundException;
import ru.feedspace.application.mapper.PostMapper;
import ru.feedspace.domain.model.Post;
import ru.feedspace.domain.model.User;
import ru.feedspace.domain.repository.PostRepository;
import ru.feedspace.domain.repository.UserRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;

    @Override
    public PostResponse createPost(PostRequest request) {
        log.info("Создание нового поста: title={}, authorId={}",
                request.getTitle(), request.getAuthorId());

        // Находим пользователя по ID
        User author = userRepository.findById(request.getAuthorId())
                .orElseThrow(() -> {
                    log.error("Пользователь с ID {} не найден", request.getAuthorId());
                    return new ResourceNotFoundException("Пользователь не найден по id: " + request.getAuthorId());
                });

        log.debug("Найден автор: {}", author.getUsername());

        // Создаем пост
        Post post = Post.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .author(author)  // Устанавливаем связь с User
                .build();

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
    public PostResponse likePost(Long postId, Long userId) {
        // TODO: обновить после создания сущности Like
        log.info("Лайк поста ID: {} от пользователя ID: {}", postId, userId);
        return null;
    }

    @Override
    public PostResponse unlikePost(Long postId, Long userId) {
        // TODO: обновить после создания сущности Like
        log.info("Удаление лайка с поста ID: {} от пользователя ID: {}", postId, userId);
        return null;
    }
}