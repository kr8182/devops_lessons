--Скрипт расширения значений поля AGE до 150

DROP TABLE IF EXISTS clients CASCADE;

CREATE TABLE clients (
                         id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
--      обязательные поля
                         name varchar NOT NULL UNIQUE,
                         age int NOT NULL check (age > 0 AND age < 150),
                         email varchar NOT NULL UNIQUE,
                         sex varchar NOT NULL,
                         phoneNumber varchar UNIQUE NOT NULL,
--      необязательные поля
                         deliveryAddress varchar,
                         description varchar,
                         favoriteGenre varchar

);