DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001);

INSERT INTO meals (datetime, description, calories, user_id)
VALUES ('2020-05-22 14:53:05', 'Breakfast', 1000, 100000),
        ('2020-05-22 14:53:05', 'Lunch', 500, 100000),
        ('2020-05-22 14:53:05', 'Dinner', 1000, 100000),
        ('2020-06-22 10:53:05', 'Breakfast', 500, 100001),
        ('2020-06-22 15:53:05', 'Lunch', 500, 100001),
        ('2020-06-22 20:53:05', 'Dinner', 500, 100001),
        ('2020-01-22 00:00:00', 'Breakfast', 1000, 100001),
        ('2020-01-22 14:53:05', 'Lunch', 500, 100001),
       ('2020-01-22 23:59:00', 'Dinner', 500, 100001);