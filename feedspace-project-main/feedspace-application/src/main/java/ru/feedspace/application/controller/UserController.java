package ru.feedspace.application.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.feedspace.api.dto.user.UserRequest;
import ru.feedspace.api.dto.user.UserResponse;
import ru.feedspace.application.service.user.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody UserRequest request) {
        log.info("POST /api/users: создание пользователя {}", request.getUsername());
        return userService.createUser(request);
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        log.info("GET /api/users: получение всех пользователей");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        log.info("GET /api/users/{}: получение пользователя", id);
        return userService.getUserById(id);
    }
}