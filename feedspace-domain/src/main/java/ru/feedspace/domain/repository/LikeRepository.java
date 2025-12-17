package ru.feedspace.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.feedspace.domain.model.Like;
import ru.feedspace.domain.model.Post;
import ru.feedspace.domain.model.User;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    // Найти лайк по пользователю и посту
    Optional<Like> findByPostAndUser(Post post, User user);

    // Проверить существует ли лайк
    boolean existsByPostAndUser(Post post, User user);

    // Количество лайков у поста
    Long countByPost(Post post);

    // Удалить лайк по пользователю и посту
    void deleteByPostAndUser(Post post, User user);

    // Удалить все лайки поста (например при удалении поста)
    void deleteAllByPost(Post post);
}