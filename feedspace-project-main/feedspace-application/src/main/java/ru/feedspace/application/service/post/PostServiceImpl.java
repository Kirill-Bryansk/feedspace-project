package ru.feedspace.application.service.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.feedspace.api.dto.post.PostResponse;
import ru.feedspace.api.dto.post.PostRequest;
import ru.feedspace.api.exceptions.ResourceNotFoundException;
import ru.feedspace.application.mapper.PostMapper;
import ru.feedspace.domain.model.Like;
import ru.feedspace.domain.model.Post;
import ru.feedspace.domain.model.User;
import ru.feedspace.domain.repository.LikeRepository;
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
    private final LikeRepository likeRepository;

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
        log.info("Лайк поста ID: {} от пользователя ID: {}", postId, userId);

        // Находим пост и пользователя
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        // Проверяем, не лайкнул ли уже
        if (likeRepository.existsByPostAndUser(post, user)) {
            log.warn("Пользователь {} уже лайкнул пост {}", userId, postId);
            throw new RuntimeException("Post already liked by user");
        }

        // Создаем лайк
        Like like = Like.builder()
                .post(post)
                .user(user)
                .build();

        likeRepository.save(like);

        // Обновляем счетчик лайков
        Long likesCount = likeRepository.countByPost(post);
        post.setLikesCount(likesCount.intValue());
        Post savedPost = postRepository.save(post);

        log.info("Лайк добавлен. Новое количество лайков: {}", likesCount);

        return postMapper.toResponse(savedPost, userId);  // Нужно обновить маппер
    }

    @Override
    public PostResponse unlikePost(Long postId, Long userId) {
        log.info("Удаление лайка с поста ID: {} от пользователя ID: {}", postId, userId);

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        // Проверяем существует ли лайк
        Like like = likeRepository.findByPostAndUser(post, user)
                .orElseThrow(() -> {
                    log.warn("Лайк не найден для пользователя {} и поста {}", userId, postId);
                    return new ResourceNotFoundException("Like not found");
                });

        // Удаляем лайк
        likeRepository.delete(like);

        // Обновляем счетчик
        Long likesCount = likeRepository.countByPost(post);
        post.setLikesCount(likesCount.intValue());
        Post savedPost = postRepository.save(post);

        log.info("Лайк удален. Новое количество лайков: {}", likesCount);

        return postMapper.toResponse(savedPost, userId);
    }
}