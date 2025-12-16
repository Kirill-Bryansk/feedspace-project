CREATE TABLE IF NOT EXISTS posts (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    image_url VARCHAR(500),
    author_name VARCHAR(100),
    author_avatar VARCHAR(500),
    likes_count INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Индекс для быстрого поиска по дате создания
CREATE INDEX IF NOT EXISTS idx_posts_created_at ON posts(created_at DESC);