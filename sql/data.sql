-- commits
-- 1
INSERT INTO commits (parent_commit_id, name, description, stable, deleted, created_time)
VALUES (NULL, 'стабильная', 'по результатам согласования протокол №54 от 2023-03-31', TRUE, FALSE, now());
-- 2
INSERT INTO commits (parent_commit_id, name, description, stable, deleted, created_time)
VALUES (1, 'Test A', 'Вопрос активен', FALSE, FALSE, now());
-- 3
INSERT INTO commits (parent_commit_id, name, description, stable, deleted, created_time)
VALUES (1, 'Test B', 'Вопрос деактивирован', FALSE, FALSE, now());

-- segment_entrypoint
-- 1
INSERT INTO segment_entrypoint (commit_id, name, description, type_entrypoint)
VALUES (1, 'Onboarding', 'Сегменты работодателя', 'INNER');
-- 2
INSERT INTO segment_entrypoint (commit_id, name, description, type_entrypoint)
VALUES (1, 'Создание Резюме', 'Сегменты соискателя', 'INNER');

-- segments
-- 1
INSERT INTO segments (commit_id, segment_entrypoint_id, parent_segment_id, role_id, key_value, name, description)
VALUES (1, 1, NULL, 123123, 'key_value', 'Торговля', 'Все что связанно с торговлей и магазинами');
-- 2
INSERT INTO segments (commit_id, segment_entrypoint_id, parent_segment_id, role_id, key_value, name, description)
VALUES (1, 1, 1, 123124, 'key_value', 'Администратор магазина', 'Управляющий персонал');
-- 3
INSERT INTO segments (commit_id, segment_entrypoint_id, parent_segment_id, role_id, key_value, name, description)
VALUES (2, 2, 1, 123124, 'key_value', 'Грузчик/экспедитор', 'Обслуживающий персонал');
-- 4
INSERT INTO segments (commit_id, segment_entrypoint_id, parent_segment_id, role_id, key_value, name, description)
VALUES (3, 2, NULL, 123124, 'key_value', 'Грузоперевозки', 'Транспортировка грузов');
-- 5
INSERT INTO segments (commit_id, segment_entrypoint_id, parent_segment_id, role_id, key_value, name, description)
VALUES (3, 2, 4, 123124, 'key_value', 'Водитель Камаза', 'Водитель с прицепом');
-- 8
INSERT INTO segments (commit_id, segment_entrypoint_id, parent_segment_id, role_id, key_value, name, description)
VALUES (3, 2, 4, 123124, 'key_value', 'Водитель Фуры', 'Водитель грузовика');

--questions
-- 1
INSERT INTO questions (commit_id, name, description, type_answer, active, required)
VALUES (2, 'Укажите разряд грузчика', 'Разряд грузчика', 'LIST', TRUE, TRUE);
-- 2
INSERT INTO questions (commit_id, name, description, type_answer, active, required)
VALUES (2, 'Укажите разряд повара', 'Разряд грузчика', 'RADIO', TRUE, TRUE);
-- 3
INSERT INTO questions (commit_id, name, description, type_answer, active, required)
VALUES (3, 'Виды транспортных средств', 'Разрешение на управление', 'CHECKBOX', TRUE, TRUE);
-- 4
INSERT INTO questions (commit_id, name, description, type_answer, active, required)
VALUES (3, 'Реквизиты документа', 'серия номер водительских прав', 'TEXT', TRUE, TRUE);
-- 5
INSERT INTO questions (commit_id, name, description, type_answer, active, required)
VALUES (3, 'active FALSE', 'серия номер водительских прав', 'TEXT', FALSE, TRUE);
-- 6
INSERT INTO questions (commit_id, name, description, type_answer, active, required)
VALUES (2, 'фото/скан удостоверения повора', 'фото/скан водительских прав', 'FILE', TRUE, TRUE);
-- 7
INSERT INTO questions (commit_id, name, description, type_answer, active, required)
VALUES (1, 'required FALSE', 'фото/скан водительских прав', 'FILE', TRUE, FALSE);
-- 8
INSERT INTO questions (commit_id, name, description, type_answer, active, required)
VALUES (1, 'active FALSE, required FALSE', 'фото/скан водительских прав', 'FILE', FALSE, FALSE);
-- 9
INSERT INTO questions (commit_id, name, description, type_answer, active, required)
VALUES (2, 'У вас есть удостоверение Экспедитора', 'Умный грузчик', 'CHECKBOX', TRUE, TRUE);

--- answers
-- к 1 вопросу
INSERT INTO answers (commit_id, question_id, question_next_id, name, description, checked)
VALUES (1, 1, NULL, 'Отсутствует', 'нет доп вопроса.', FALSE);
INSERT INTO answers (commit_id, question_id, question_next_id, name, description, checked)
VALUES (1, 1, 9, '1', 'дополнительный вопрос #9 (У вас есть удостоверение Экспедитора)', FALSE);
INSERT INTO answers (commit_id, question_id, question_next_id, name, description, checked)
VALUES (1, 1, 3, '2', 'дополнительный вопрос #3 (реквизиты документа)', FALSE);
INSERT INTO answers (commit_id, question_id, question_next_id, name, description, checked)
VALUES (1, 1, 6, 'Высшей категории', 'дополнительный вопрос #6 (фото.скан)', FALSE);
-- к 2 вопросу
INSERT INTO answers (commit_id, question_id, question_next_id, name, description, checked)
VALUES (1, 2, NULL, 'Отсутствует', 'нет дополнительно вопроса!', FALSE);
INSERT INTO answers (commit_id, question_id, question_next_id, name, description, checked)
VALUES (1, 2, 6, 'Высшей категории', 'дополнительный вопрос #6 (фото.скан)', FALSE);
INSERT INTO answers (commit_id, question_id, question_next_id, name, description, checked)
VALUES (1, 2, 6, 'radio3вариант', 'дополнительный вопрос #6 (фото.скан)', FALSE);
-- к 9 вопросу
INSERT INTO answers (commit_id, question_id, question_next_id, name, description, checked)
VALUES (1, 1, NULL, 'Да', 'нет доп вопроса.', FALSE);
INSERT INTO answers (commit_id, question_id, question_next_id, name, description, checked)
VALUES (1, 1, NULL, 'Нет', 'нет доп вопроса.', FALSE);

-- history
INSERT INTO history (user_id, name_db, time, type, description)
VALUES (141, 'answers', now(), 'CREATED', '{id:1, name:"Да", question_id:3, NULL, FALSE}');

INSERT INTO history (user_id, name_db, time, type, description)
VALUES (142, 'answers', now(), 'EDIT', '{id:1, name:"Нет", question_id:3, NULL, FALSE}');

INSERT INTO history (user_id, name_db, time, type, description)
VALUES (143, 'answers', now(), 'DELETE', '{id:1}');
