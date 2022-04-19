INSERT INTO USERS (email, first_name, last_name, password)
VALUES ('admin@yandex.ru', 'Admin_First_Name', 'Admin_Last_Name', 'Admin_Password'),
       ('firstuser@yandex.ru', 'User1_First_Name', 'User1_Last_Name', 'User1_Password');

INSERT INTO USER_ROLES (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2)
