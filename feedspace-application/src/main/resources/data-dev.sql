-- Очищаем таблицу перед вставкой тестовых данных
DELETE FROM posts;

-- Вставляем тестовые данные
INSERT INTO posts (title, description, image_url, author_name, author_avatar, likes_count, created_at)
VALUES
('Отдам книги', 'Классическая литература, в хорошем состоянии. Доставка самовывозом.', 'https://picsum.photos/seed/book/600/400', 'Иван Петров', 'https://api.dicebear.com/7.x/avataaars/svg?seed=Ivan', 5, '2024-02-15 14:30:00'),
('Смартфон XYZ', 'Мощный процессор, отличная камера. Б/у, 1 год.', 'https://picsum.photos/seed/phone/600/400', 'TechGadgets', 'https://api.dicebear.com/7.x/avataaars/svg?seed=Tech', 42, '2024-02-14 09:15:00'),
('Красивый закат', 'Фотография с отпуска на Байкале. Природа прекрасна!', 'https://picsum.photos/seed/sunset/600/400', 'Мария Сидорова', 'https://api.dicebear.com/7.x/avataaars/svg?seed=Maria', 128, '2024-02-10 18:45:00');