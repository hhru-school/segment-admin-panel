--Первый слой
INSERT INTO layers (parent_layer_id, title, description, state, create_time)
VALUES (NULL, 'Первый слой', 'Базовый слой', 'STABLE', '2023-04-12 02:02:00'); -- 1

--Второй слой
INSERT INTO layers (parent_layer_id, title, description, state, create_time)
VALUES (1, 'Второй слой', 'Слой, унаследованный от базового слоя', 'STABLE', '2023-04-13 02:02:00'); -- 2

--Третий слой
INSERT INTO layers (parent_layer_id, title, description, state, create_time)
VALUES (2, 'Третий слой', 'Слой, унаследованный от второго слоя', 'STABLE', '2023-04-13 02:02:00'); -- 3

--Четвертый слой, архивный
INSERT INTO layers (parent_layer_id, title, description, state, create_time)
VALUES (3, 'Четвертый слой', 'Слой, унаследованный от третьего слоя, архивный', 'ARCHIVE', '2023-04-15 02:02:00'); -- 4

--Пятый слой
INSERT INTO layers (parent_layer_id, title, description, state, create_time)
VALUES (3, 'Пятый слой', 'Слой, унаследованный от третьего слоя', 'STABLE', '2023-04-16 02:02:00'); -- 5

--Шестой слой, удаленный
INSERT INTO layers (parent_layer_id, title, description, state, create_time)
VALUES (5, 'Шестой слой', 'Слой, унаследованный от пятого слоя, удаленный', 'TEST', '2023-04-17 02:02:00'); -- 6

--Седьмой слой, экспериментальный
INSERT INTO layers (parent_layer_id, title, description, state, create_time)
VALUES (5, 'Седьмой слой', 'Слой, унаследованный от пятого слоя, экспериментальный', 'TEST', '2023-04-18 02:02:00'); -- 7
