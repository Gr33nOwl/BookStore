INSERT INTO USERS (email, first_name, last_name, password)
VALUES ('admin@yandex.ru', 'Admin_First_Name', 'Admin_Last_Name', 'Admin_Password'),
       ('first_user@yandex.ru', 'User1_First_Name', 'User1_Last_Name', 'User1_Password');

INSERT INTO AUTHORS(first_name, last_name)
VALUES ('Алексей', 'Пехов'),
       ('Фрэнк', 'Герберт'),
       ('Кир', 'Булычев'),
       ('Марио', 'Пьюзо'),
       ('Косюн', 'Таками'),
       ('Елена', 'Бычкова');

INSERT INTO BOOKS (title, publication_date, price, amount)
VALUES ('Страж', '2010-11-15', '259', '15'),
       ('Аутодафе', '2011-09-12', '200', '10'),
       ('Золотые костры', '2012-02-27', '320', '12'),
       ('Проклятый горн', '2014-01-01', '310', '9'),
       ('Дюна', '1965-12-01', '120', '5'),
       ('Мессия дюны', '1969-08-05', '110', '11'),
       ('Дети дюны', '1976-03-01', '75', '15'),
       ('Бог-Император Дюны', '1981-03-04', '100', '30'),
       ('Еретики Дюны', '1984-04-01', '200', '18'),
       ('Капитул Дюны', '1985-04-07', '210', '25'),
       ('Девочка, с которой ничего не случится', '1965-12-01', '300', '2'),
       ('Ржавый фельдмаршал', '1967-11-04', '330', '4'),
       ('Путешествие Алисы', '1974-07-15', '400', '9'),
       ('Крестный отец', '1969-01-15', '230', '10'),
       ('Королевская битва', '2018-04-09', '220', '25');

INSERT INTO BOOK_AUTHORS (book_id, author_id)
VALUES (1, 1),
       (1, 6),
       (2, 1),
       (2, 6),
       (3, 1),
       (4, 1),
       (5, 2),
       (6, 2),
       (7, 2),
       (8, 2),
       (9, 2),
       (10, 2),
       (11, 3),
       (12, 3),
       (13, 3),
       (14, 4),
       (15, 5);

INSERT INTO USER_ROLES (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 1),
       ('USER', 2);


INSERT INTO BOOK_GENRES (genre, book_id)
VALUES ('FANTASY', 1),
       ('MYSTERY', 1),
       ('FABLE', 1),
       ('FANTASY', 2),
       ('MYSTERY', 2),
       ('FABLE', 2),
       ('FANTASY', 3),
       ('MYSTERY', 3),
       ('FABLE', 3),
       ('FANTASY', 4),
       ('MYSTERY', 4),
       ('FABLE', 4),
       ('SCIENCE_FICTION', 5),
       ('CLASSIC', 5),
       ('ROMANCE', 5),
       ('SCIENCE_FICTION', 6),
       ('CLASSIC', 6),
       ('ROMANCE', 6),
       ('SCIENCE_FICTION', 7),
       ('CLASSIC', 7),
       ('ROMANCE', 7),
       ('SCIENCE_FICTION', 8),
       ('CLASSIC', 8),
       ('ROMANCE', 8),
       ('SCIENCE_FICTION', 9),
       ('CLASSIC', 9),
       ('ROMANCE', 9),
       ('SCIENCE_FICTION', 10),
       ('CLASSIC', 10),
       ('ROMANCE', 10),
       ('FABLE', 11),
       ('CLASSIC', 11),
       ('SCIENCE_FICTION', 11),
       ('FABLE', 12),
       ('CLASSIC', 12),
       ('SCIENCE_FICTION', 12),
       ('FABLE', 13),
       ('CLASSIC', 13),
       ('SCIENCE_FICTION', 13),
       ('ACTION', 14),
       ('CLASSIC', 14),
       ('DRAMA', 14),
       ('ROMANCE', 14),
       ('CRIME', 14),
       ('ACTION', 15),
       ('HORROR', 15),
       ('COMIC', 15);






