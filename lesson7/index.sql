CREATE INDEX books_genreid_author_id
ON books(genre_id, author_id);

--Выбран индекс по жанрам и авторам, так как он уменшил cost запроса с 61.24 до 45.06