-- branches
INSERT INTO branches (parent_branch_id, name, description, stable, deleted, created_time)
VALUES (NULL, 'стабильная', 'по результатам согласования протокол №54 от 2023-03-31', TRUE, FALSE, now());

INSERT INTO branches (parent_branch_id, name, description, stable, deleted, created_time)
VALUES (1, 'Test A', 'Укажите разряд VS Ваш разряд', FALSE, FALSE, now());

INSERT INTO branches (parent_branch_id, name, description, stable, deleted, created_time)
VALUES (1, 'Test B', 'Водительское удостоверение VS Удостоверение водителя', FALSE, FALSE, now());

-- segments
-- 1
INSERT INTO segments (name, description, branch_id, parent_segment_id, role_id, key_value)
VALUES ('Торговля', 'Все что связанно с торговлей и магазинами', 1, NULL, NULL, NULL);
-- 2
INSERT INTO segments (name, description, branch_id, parent_segment_id, role_id, key_value)
VALUES ('Администратор магазина', 'Управляющий персонал', 1, 1, 10234, 'Заведующий');
-- 3
INSERT INTO segments (name, description, branch_id, parent_segment_id, role_id, key_value)
VALUES ('Грузчик/экспедитор', 'Обслуживающий персонал', 2, 1, 21234, 'Грузчик');
-- 4
INSERT INTO segments (name, description, branch_id, parent_segment_id, role_id, key_value)
VALUES ('Грузоперевозки', 'Транспортировка грузов', 1, NULL, NULL, NULL);

-- question
-- 1
INSERT INTO questions (name, description, segment_id, type_owner, type_answer, active, required)
VALUES ('Укажите разряд', 'Разряд грузчика', 3, 'ONBOARD', 'LIST', TRUE, TRUE);
-- 2
INSERT INTO questions (name, description, segment_id, type_owner, type_answer, active, required)
VALUES ('Ваш разряд', 'Разряд грузчика', 3, 'ONBOARD', 'LIST', TRUE, TRUE);
-- 3
INSERT INTO questions (name, description, segment_id, type_owner, type_answer, active, required)
VALUES ('Серия и номер удостоверения', 'реквизиты документа', 3, 'ONBOARD', 'TEXT', TRUE, TRUE);
-- 4
INSERT INTO questions (name, description, segment_id, type_owner, type_answer, active, required)
VALUES ('Разрешение на работу в РФ', '', 3, 'ONBOARD', 'RADIO', TRUE, TRUE);

--- answers
-- к 1 вопросу
INSERT INTO answers (name, description, question_id, question_next_id, checked)
VALUES ('Отсутствует', NULL, 1, 3, FALSE);

INSERT INTO answers (name, description, question_id, question_next_id, checked)
VALUES ('Высшей категории', NULL, 1, 3, FALSE);

INSERT INTO answers (name, description, question_id, question_next_id, checked)
VALUES ('Грузчик/Экспедитор', NULL, 1, 3, FALSE);

-- к 2 вопросу
INSERT INTO answers (name, description, question_id, question_next_id, checked)
VALUES ('Отсутствует', NULL, 2, 3, FALSE);

INSERT INTO answers (name, description, question_id, question_next_id, checked)
VALUES ('Высшей категории', NULL, 2, 3, FALSE);

INSERT INTO answers (name, description, question_id, question_next_id, checked)
VALUES ('Грузчик/Экспедитор', NULL, 2, 3, FALSE);
-- к 3 вопросу
INSERT INTO answers (name, description, question_id, question_next_id, checked)
VALUES ('серия - номер', NULL, 3, 4, FALSE);

-- к 4 вопросу
INSERT INTO answers (name, description, question_id, question_next_id, checked)
VALUES ('Нет', NULL, 4, NULL, FALSE);

INSERT INTO answers (name, description, question_id, question_next_id, checked)
VALUES ('Да', NULL, 4, NULL, FALSE);

-- history
INSERT INTO history (type, name_db, user_id, description, time)
VALUES ('CREATED', 'answers', 141, '{id:1, name:"Да", question_id:3, NULL, FALSE}', now());

INSERT INTO history (type, name_db, user_id, description, time)
VALUES ('EDIT', 'answers', 141, '{id:2, name:"Нет", question_id:3, NULL, FALSE}', now());

INSERT INTO history (type, name_db, user_id, description, time)
VALUES ('DELETED', 'answers', 141, '{id:1}', now());
