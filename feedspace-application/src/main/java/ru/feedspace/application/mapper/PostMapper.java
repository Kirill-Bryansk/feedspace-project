package ru.feedspace.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.feedspace.api.dto.post.PostRequest;
import ru.feedspace.api.dto.post.PostResponse;
import ru.feedspace.domain.model.Post;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "likesCount", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Post toEntity(PostRequest request);

    // Старый метод (без userId) - можно оставить для совместимости
    default PostResponse toResponse(Post post) {
        return toResponse(post, null);
    }

    // Новый метод с userId
    @Mapping(target = "isLiked", expression = "java(isPostLikedByUser(post, userId))")
    PostResponse toResponse(Post post, Long userId);

    // Проверка лайкнул ли пользователь пост
    default Boolean isPostLikedByUser(Post post, Long userId) {
        if (userId == null || post.getLikes() == null) {
            return false;
        }
        return post.getLikes().stream()
                .anyMatch(like -> like.getUser().getId().equals(userId));
    }
}