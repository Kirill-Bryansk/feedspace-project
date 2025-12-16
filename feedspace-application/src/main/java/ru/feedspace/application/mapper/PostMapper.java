package ru.feedspace.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.feedspace.api.dto.post.PostRequest;
import ru.feedspace.api.dto.post.PostResponse;
import ru.feedspace.domain.model.Post;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)      // Игнорируем - устанавливаем в сервисе
    @Mapping(target = "likesCount", ignore = true)  // По умолчанию 0 в Entity
    @Mapping(target = "createdAt", ignore = true)   // Установится автоматически
    Post toEntity(PostRequest request);

    @Mapping(target = "isLiked", constant = "false") // Пока всегда false
    PostResponse toResponse(Post post);
}