package ru.feedspace.api.dto.post;

import lombok.Data;

@Data
public class PostRequest {
    private String title;
    private String description;
    private String imageUrl;
    private Long authorId;
}
