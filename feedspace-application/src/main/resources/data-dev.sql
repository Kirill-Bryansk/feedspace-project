-- data-dev.sql
-- Вставляем пользователей (БЕЗ указания id - база сама сгенерирует!)
INSERT INTO users (username, avatar_url, registered_at) VALUES
('Иван Петров', 'https://api.dicebear.com/7.x/avataaars/svg?seed=Ivan', '2024-01-01 10:00:00'),
('TechGadgets', 'https://api.dicebear.com/7.x/avataaars/svg?seed=Tech', '2024-01-02 11:00:00'),
('Мария Сидорова', 'https://api.dicebear.com/7.x/avataaars/svg?seed=Maria', '2024-01-03 12:00:00'),
('API Тестер', 'https://api.dicebear.com/7.x/avataaars/svg?seed=API', '2024-01-04 13:00:00');

-- Вставляем посты (author_id должен соответствовать реальным id пользователей)
-- В H2 вам нужно получить реальные id пользователей
-- Вот правильный способ:

-- Способ 1: Сначала получить id пользователей, затем использовать их
INSERT INTO posts (title, description, image_url, author_id, likes_count, created_at)
SELECT
    'Отдам книги',
    'Классическая литература, в хорошем состоянии. Доставка самовывозом.',
    'https://picsum.photos/seed/book/600/400',
    u.id,
    5,
    '2024-02-15 14:30:00'
FROM users u WHERE u.username = 'Иван Петров'
UNION ALL
SELECT
    'Смартфон XYZ',
    'Мощный процессор, отличная камера. Б/у, 1 год.',
    'https://picsum.photos/seed/phone/600/400',
    u.id,
    42,
    '2024-02-14 09:15:00'
FROM users u WHERE u.username = 'TechGadgets'
UNION ALL
SELECT
    'Красивый закат',
    'Фотография с отпуска на Байкале. Природа прекрасна!',
    'https://picsum.photos/seed/sunset/600/400',
    u.id,
    128,
    '2024-02-10 18:45:00'
FROM users u WHERE u.username = 'Мария Сидорова';