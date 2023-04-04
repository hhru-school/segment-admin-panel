-- commits
-- 1
INSERT INTO commits (parent_commit_id, title, description, stable, deleted, created_time)
VALUES (NULL, 'стабильная', 'по результатам согласования протокол №54 от 2023-03-31', TRUE, FALSE, now());
-- 2
INSERT INTO commits (parent_commit_id, title, description, stable, deleted, created_time)
VALUES (1, 'Test A', 'Вопрос активен', FALSE, FALSE, now());
-- 3
INSERT INTO commits (parent_commit_id, title, description, stable, deleted, created_time)
VALUES (1, 'Test B', 'Вопрос деактивирован', FALSE, FALSE, now());

-- segment_entrypoint
-- 1
INSERT INTO segment_entrypoint (commit_id, title, description, type_entrypoint)
VALUES (1, 'Onboarding', 'Сегменты работодателя', 'INNER');
-- 2
INSERT INTO segment_entrypoint (commit_id, title, description, type_entrypoint)
VALUES (1, 'Создание Резюме', 'Сегменты соискателя', 'INNER');

-- segments
-- 1
INSERT INTO segments (commit_id, parent_segment_id, role_id, title, description)
VALUES (1, NULL, 123123, 'Торговля', 'Все что связанно с торговлей и магазинами');
-- 2
INSERT INTO segments (commit_id, parent_segment_id, role_id, title, description)
VALUES (1, 1, 123124, 'Администратор магазина', 'Управляющий персонал');
-- 3
INSERT INTO segments (commit_id, parent_segment_id, role_id, title, description)
VALUES (2, 1, 123124, 'Грузчик/экспедитор', 'Обслуживающий персонал');
-- 4
INSERT INTO segments (commit_id, parent_segment_id, role_id, title, description)
VALUES (3, NULL, 123124, 'Грузоперевозки', 'Транспортировка грузов');
-- 5
INSERT INTO segments (commit_id, parent_segment_id, role_id, title, description)
VALUES (3, 4, 123124, 'Водитель Камаза', 'Водитель с прицепом');
-- 8
INSERT INTO segments (commit_id, parent_segment_id, role_id, title, description)
VALUES (3, 4, 123124, 'Водитель Фуры', 'Водитель грузовика');

--questions
-- 1
INSERT INTO questions (commit_id, title, description, question_type, active, required)
VALUES (2, 'Укажите разряд грузчика', 'Разряд грузчика', 'LIST', TRUE, TRUE);
-- 2
INSERT INTO questions (commit_id, title, description, question_type, active, required)
VALUES (2, 'Укажите разряд повара', 'Разряд грузчика', 'RADIO', TRUE, TRUE);
-- 3
INSERT INTO questions (commit_id, title, description, question_type, active, required)
VALUES (3, 'Виды транспортных средств', 'Разрешение на управление', 'CHECKBOX', TRUE, TRUE);
-- 4
INSERT INTO questions (commit_id, title, description, question_type, active, required)
VALUES (3, 'Реквизиты документа', 'серия номер водительских прав', 'TEXT', TRUE, TRUE);
-- 5
INSERT INTO questions (commit_id, title, description, question_type, active, required)
VALUES (3, 'active FALSE', 'серия номер водительских прав', 'TEXT', FALSE, TRUE);
-- 6
INSERT INTO questions (commit_id, title, description, question_type, active, required)
VALUES (2, 'фото/скан удостоверения повора', 'фото/скан водительских прав', 'FILE', TRUE, TRUE);
-- 7
INSERT INTO questions (commit_id, title, description, question_type, active, required)
VALUES (1, 'required FALSE', 'фото/скан водительских прав', 'FILE', TRUE, FALSE);
-- 8
INSERT INTO questions (commit_id, title, description, question_type, active, required)
VALUES (1, 'active FALSE, required FALSE', 'фото/скан водительских прав', 'FILE', FALSE, FALSE);
-- 9
INSERT INTO questions (commit_id, title, description, question_type, active, required)
VALUES (2, 'У вас есть удостоверение Экспедитора', 'Умный грузчик', 'CHECKBOX', TRUE, TRUE);

--- answers
-- к 1 вопросу
INSERT INTO answers (commit_id, open_questions, title, is_default_answer)
VALUES (1, NULL, 'Отсутствует - нет доп вопроса.', FALSE); --
INSERT INTO answers (commit_id, open_questions, title, is_default_answer)
VALUES (1, array [9], '1 - дополнительный вопрос #9 (У вас есть удостоверение Экспедитора)', FALSE);
INSERT INTO answers (commit_id, open_questions, title, is_default_answer)
VALUES (1, array [3], '2 - дополнительный вопрос #3 (реквизиты документа)', FALSE);
INSERT INTO answers (commit_id, open_questions, title, is_default_answer)
VALUES (1, array [6], 'Высшей категории - дополнительный вопрос #6 (фото.скан)', FALSE);
-- к 2 вопросу
INSERT INTO answers (commit_id, open_questions, title, is_default_answer)
VALUES (1, NULL, 'Отсутствует - нет дополнительно вопроса!', FALSE);
INSERT INTO answers (commit_id, open_questions, title, is_default_answer)
VALUES (1, array [6], 'Высшей категории - дополнительный вопрос #6 (фото.скан)', FALSE);
INSERT INTO answers (commit_id, open_questions, title, is_default_answer)
VALUES (1, array [6], 'radio3вариант - дополнительный вопрос #6 (фото.скан)', FALSE);
-- к 9 вопросу
INSERT INTO answers (commit_id, open_questions, title, is_default_answer)
VALUES (1, NULL, 'Да - нет доп вопроса.', FALSE);
INSERT INTO answers (commit_id, open_questions, title, is_default_answer)
VALUES (1, NULL, 'Нет - нет доп вопроса.', FALSE);

-- history
INSERT INTO history (user_id, name_db, time, type, description)
VALUES (141, 'answers', now(), 'CREATED', '{id:1, title:"Да", question_id:3, NULL, FALSE}');

INSERT INTO history (user_id, name_db, time, type, description)
VALUES (142, 'answers', now(), 'EDIT', '{id:1, title:"Нет", question_id:3, NULL, FALSE}');

INSERT INTO history (user_id, name_db, time, type, description)
VALUES (143, 'answers', now(), 'DELETE', '{id:1}');
