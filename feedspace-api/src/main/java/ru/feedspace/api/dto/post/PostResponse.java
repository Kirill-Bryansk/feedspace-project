package ru.feedspace.api.dto.post;

import lombok.Data;
import ru.feedspace.api.dto.user.UserResponse;

import java.time.LocalDateTime;

@Data
public class PostResponse {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private UserResponse author;
    private Integer likesCount;
    private Boolean isLiked;
    private LocalDateTime createdAt;
}
