package ru.feedspace.api.dto.user;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String avatarUrl;
}