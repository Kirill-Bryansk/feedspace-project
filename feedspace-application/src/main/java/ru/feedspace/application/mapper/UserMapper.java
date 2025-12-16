package ru.feedspace.application.mapper;

import org.mapstruct.Mapper;
import ru.feedspace.api.dto.user.UserResponse;
import ru.feedspace.domain.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toResponse(User user);
}