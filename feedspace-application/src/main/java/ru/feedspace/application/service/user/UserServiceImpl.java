package ru.feedspace.application.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.feedspace.api.dto.user.UserRequest;
import ru.feedspace.api.dto.user.UserResponse;
import ru.feedspace.api.exceptions.ResourceNotFoundException;
import ru.feedspace.application.mapper.UserMapper;
import ru.feedspace.domain.model.User;
import ru.feedspace.domain.repository.UserRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public UserResponse createUser(UserRequest request) {
        log.info("Создание нового пользователя: {}", request.getUsername());

        // Проверяем, не существует ли уже пользователь с таким username
        if (repository.existsByUsername(request.getUsername())) {
            log.error("Пользователь с username {} уже существует", request.getUsername());
            throw new RuntimeException("User with username " + request.getUsername() + " already exists");
            // Можно создать отдельное исключение: UserAlreadyExistsException
        }

        User user = mapper.toUser(request);
        User savedUser = repository.save(user);
        log.info("Пользователь сохранен в БД с ID: {}", savedUser.getId());

        UserResponse response = mapper.toResponse(savedUser);
        log.debug("Создан UserResponse: {}", response);

        return response;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        log.info("Получение всех пользователей из БД");
        List<User> users = repository.findAll();
        log.debug("Найдено {} пользователей", users.size());
        return users.stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public UserResponse getUserById(Long id) {
        log.info("Получение пользователя по ID: {}", id);
        User user = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("Пользователь с ID {} не найден", id);
                    return new ResourceNotFoundException("User not found with id: " + id);
                });
        return mapper.toResponse(user);
    }
}