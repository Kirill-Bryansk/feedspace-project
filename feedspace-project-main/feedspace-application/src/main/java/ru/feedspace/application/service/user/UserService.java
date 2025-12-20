package ru.feedspace.application.service.user;

import ru.feedspace.api.dto.user.UserRequest;
import ru.feedspace.api.dto.user.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);
}
