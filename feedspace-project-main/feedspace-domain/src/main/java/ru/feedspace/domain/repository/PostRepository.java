package ru.feedspace.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.feedspace.domain.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
