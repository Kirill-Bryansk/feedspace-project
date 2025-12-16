package ru.feedspace.api.dto;

import lombok.Data;

@Data
public class PostRequest {
    private String title;
    private String description;
    private String imageUrl;
    private String authorName;
    private String authorAvatar;
}
