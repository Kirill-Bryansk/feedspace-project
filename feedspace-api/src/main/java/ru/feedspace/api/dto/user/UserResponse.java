package ru.feedspace.api.dto.user;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String avatarUrl;
    private LocalDateTime registeredAt;
}