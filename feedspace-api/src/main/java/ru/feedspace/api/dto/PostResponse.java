package ru.feedspace.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponse {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private String authorName;
    private String authorAvatar;
    private Integer likesCount;
    private Boolean isLiked;
    private LocalDateTime createdAt;
}
