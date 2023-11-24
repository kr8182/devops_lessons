CREATE INDEX books_genreid_author_id
ON books(genre_id, author_id);

CREATE INDEX books_booksid_clientid
ON books(books_id, client_id);