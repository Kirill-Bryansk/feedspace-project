package ru.feedspace.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.feedspace.api.dto.PostRequest;
import ru.feedspace.api.dto.PostResponse;
import ru.feedspace.domain.model.Post;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "likesCount", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Post toEntity(PostRequest request);

    @Mapping(target = "isLiked", constant = "false")
    PostResponse toResponse(Post post);
}