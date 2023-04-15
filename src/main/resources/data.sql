--Первый слой
INSERT INTO layers (parent_layer_id, title, description, layer_stable, layer_archive, layer_deleted, create_time)
VALUES
(NULL, 'Первый слой', 'Базовый слой', TRUE, FALSE, FALSE, '2023-04-12 02:02:00');

INSERT INTO entrypoints (layer_id, title, description, entrypoint_type)
VALUES
(1, 'Onboarding', 'Размещение вакансии работодателем', 1),
(1, 'Резюме', 'Размещение резюме соискателем', 2);

INSERT INTO segments (layer_id, parent_segment_id, title, description, role, tag)
VALUES
(1, NULL, 'Программист, разработчик', 'Разработчик программного обеспечения', '{96}', '{"Programmer", "Software", "IT"}'); --1

INSERT INTO questions (layer_id, question_title, question_type, description, possible_answers, question_required, question_visibility)
VALUES
(1, 'Опыт работы, лет', 'SINGLE_CHOICE', 'Опыт работы на аналогичной должности, указанный в годах', '{1, 2, 3, 7}', TRUE, 'SHOW'), --1
(1, 'Высшее образование', 'SINGLE_CHOICE', 'Наличие высшего образования', '{4, 5, 6}', TRUE, 'SHOW'); --2

INSERT INTO answers (layer_id, open_questions, title, POSITIVE_title, answer_type, is_default_answer, skip_at_result)
VALUES
(1, NULL, 'Менее года', 'Менее года', 'POSITIVE', FALSE, FALSE), --1
(1, NULL, 'От 1 до 3 лет', 'От 1 до 3 лет', 'POSITIVE', FALSE, FALSE), --2
(1, NULL, 'От 3 до 6 лет', 'От 3 до 6 лет', 'POSITIVE', FALSE, FALSE), --3
(1, NULL, 'Нет', 'Нет высшего образования', 'NEGATIVE', FALSE, FALSE), --4
(1, NULL, 'Незаконченное', 'Незаконченное высшее образование', 'NEUTRAL', FALSE, TRUE), --5
(1, NULL, 'Есть', 'Есть высшее образование', 'POSITIVE', FALSE, FALSE), --6
(1, NULL, 'Нет опыта', 'Нет опыта подобной работы', 'NEGATIVE', FALSE, FALSE), --7
(1, NULL, 'Есть опыт', 'Есть опыт подобной работы', 'POSITIVE', FALSE, FALSE), --8
(1, NULL, 'Да', 'Утвердительный ответ', 'POSITIVE', FALSE, FALSE), --9
(1, NULL, 'Нет', 'Отрицательный ответ', 'NEGATIVE', FALSE, FALSE); --10

INSERT INTO question_activate_links (layer_id, segment_id, question_id, entrypoint_id, question_required, question_visibility)
VALUES
(1, 1, 1, 1, TRUE, 'SHOW'),
(1, 1, 2, 1, TRUE, 'SHOW'),
(1, 1, 1, 2, TRUE, 'SHOW'),
(1, 1, 2, 2, TRUE, 'SHOW');

INSERT INTO history (user_id, name_db, time, type, description)
VALUES
(1, 'segment-db', '2023-04-12 02:02:00', 'CREATE', 'Новая сессия');

--Второй слой
INSERT INTO layers (parent_layer_id, title, description, layer_stable, layer_archive, layer_deleted, create_time)
VALUES
(1, 'Второй слой', 'Слой, унаследованный от базового слоя', TRUE, FALSE, FALSE, '2023-04-13 02:02:00');

INSERT INTO segments (layer_id, parent_segment_id, title, description, role, tag)
VALUES
(2, NULL, 'Переводчик', 'Переводчик', '{93}', '{"Translator", "ForeignLanguages"}'); --2

INSERT INTO questions (layer_id, question_title, question_type, description, possible_answers, question_required, question_visibility)
VALUES
(2, 'Опыт работы за границей', 'SINGLE_CHOICE', 'Опыт работы переводчиком за границей', '{1, 2, 3, 7}', TRUE, 'SHOW'), --3
(2, 'Опыт работы на официальных мероприятиях', 'SINGLE_CHOICE', 'Опыт работы переводчиком на мероприятиях с участием официальных лиц', '{7, 8}', TRUE, 'HIDE'); --4

INSERT INTO question_activate_links (layer_id, segment_id, question_id, entrypoint_id, question_required, question_visibility)
VALUES
(2, 2, 2, 1, TRUE, 'SHOW'),
(2, 2, 3, 1, TRUE, 'SHOW'),
(2, 2, 4, 1, TRUE, 'HIDE'),
(2, 2, 2, 2, TRUE, 'SHOW'),
(2, 2, 3, 2, TRUE, 'SHOW'),
(2, 2, 4, 2, TRUE, 'HIDE');

INSERT INTO history (user_id, name_db, time, type, description)
VALUES
(2, 'segment-db', '2023-04-13 02:02:00', 'CREATE', 'Новая сессия');

--Третий слой
INSERT INTO layers (parent_layer_id, title, description, layer_stable, layer_archive, layer_deleted, create_time)
VALUES
(2, 'Третий слой', 'Слой, унаследованный от второго слоя', TRUE, FALSE, FALSE, '2023-04-13 02:02:00');

INSERT INTO segments (layer_id, parent_segment_id, title, description, role, tag)
VALUES
(3, 2, 'Переводчик с китайского', 'Переводчик, владеющмя китайским языком, унаследован от переводчика', '{93}', '{"Translator", "Chinese"}'), --3
(3, NULL, 'Охранник', 'Охранник', '{90}', '{"Security", "Safety", "Guard"}'), --4
(3, 1, 'Java-разработчик', 'Разработчик серверной части приложений на языке Java', '{96}', '{"Java", "Backend", "Programmer", "Software", "IT"}'); --5

INSERT INTO questions (layer_id, question_title, question_type, description, possible_answers, question_required, question_visibility)
VALUES
(3, 'Время владения китайским языком', 'SINGLE_CHOICE', 'Количество лет практики китайского языка', '{1, 2, 3, 7}', TRUE, 'SHOW'), --5
(3, 'Опыт проживания в Китае', 'SINGLE_CHOICE', 'Был ли опыт проживания в КНР', '{9, 10}', FALSE, 'SHOW'), --6
(3, 'Имеете ли разряд', 'SINGLE_CHOICE', 'Был ли опыт прохождения обучения в Китае', '{9, 10}', TRUE, 'SHOW'), --7
(3, 'Разряд', 'SINGLE_CHOICE', 'Имеющийся разряд по профессии', '{13, 14, 15, 16, 17, 18}', TRUE, 'SHOW'), --8
(3, 'Технологический стэк', 'MULTI_SELECT', 'Перечень технологий, которыми умеет пользоваться кандидат', '{19, 20, 21, 22}', TRUE, 'SHOW'); --9

INSERT INTO answers (layer_id, open_questions, title, POSITIVE_title, answer_type, is_default_answer, skip_at_result)
VALUES
(3, '{8}', 'Да, есть разряд', 'Нет опыта подобной работы', 'POSITIVE', FALSE, FALSE), --11
(3, NULL, 'Разряда нет', 'Есть опыт подобной работы', 'NEGATIVE', FALSE, FALSE), --12
(3, NULL, '1-й разряд', '1-й разряд', 'POSITIVE', TRUE, FALSE), --13
(3, NULL, '2-й разряд', '2-й разряд', 'POSITIVE', FALSE, FALSE), --14
(3, NULL, '3-й разряд', '3-й разряд', 'POSITIVE', FALSE, FALSE), --15
(3, NULL, '4-й разряд', '4-й разряд', 'POSITIVE', FALSE, FALSE), --16
(3, NULL, '5-й разряд', '5-й разряд', 'POSITIVE', FALSE, FALSE), --17
(3, NULL, '6-й разряд', '6-й разряд', 'POSITIVE', FALSE, FALSE), --18
(3, NULL, 'Spring Framework', 'Spring Framework', 'POSITIVE', FALSE, FALSE), --19
(3, NULL, 'Stream API', 'Stream API', 'POSITIVE', FALSE, FALSE), --20
(3, NULL, 'Hibernate', 'Hibernate', 'POSITIVE', FALSE, FALSE), --21
(3, NULL, 'Java Concurrency', 'Java Concurrency', 'POSITIVE', FALSE, FALSE); --22

INSERT INTO question_activate_links (layer_id, segment_id, question_id, entrypoint_id, question_required, question_visibility)
VALUES
(3, 3, 1, 1, TRUE, 'SHOW'),
(3, 3, 2, 1, TRUE, 'SHOW'),
(3, 3, 5, 1, TRUE, 'SHOW'),
(3, 3, 6, 1, FALSE, 'SHOW'),
(3, 3, 1, 2, TRUE, 'SHOW'),
(3, 3, 2, 2, TRUE, 'SHOW'),
(3, 3, 5, 2, TRUE, 'SHOW'),
(3, 3, 6, 2, FALSE, 'SHOW'),
(3, 4, 7, 1, TRUE, 'SHOW'),
(3, 4, 8, 1, TRUE, 'SHOW'),
(3, 4, 7, 2, TRUE, 'SHOW'),
(3, 4, 8, 2, TRUE, 'SHOW'),
(3, 5, 1, 1, TRUE, 'SHOW'),
(3, 5, 2, 1, TRUE, 'SHOW'),
(3, 5, 9, 1, TRUE, 'SHOW'),
(3, 5, 1, 2, TRUE, 'SHOW'),
(3, 5, 2, 2, TRUE, 'SHOW'),
(3, 5, 9, 2, TRUE, 'SHOW');

INSERT INTO history (user_id, name_db, time, type, description)
VALUES
(3, 'segment-db', '2023-04-14 02:02:00', 'CREATE', 'Новая сессия');

--Четвертый слой, архивный
INSERT INTO layers (parent_layer_id, title, description, layer_stable, layer_archive, layer_deleted, create_time)
VALUES
(3, 'Четвертый слой', 'Слой, унаследованный от третьего слоя, архивный', FALSE, TRUE, FALSE, '2023-04-15 02:02:00');

INSERT INTO segments (layer_id, parent_segment_id, title, description, role, tag)
VALUES
(4, 4, 'Телохранитель', 'Телохранитель, унаследован от охранника', '{90}', '{"Bodyguard", "Security", "PrivateProtection"}'); --6

INSERT INTO questions (layer_id, question_title, question_type, description, possible_answers, question_required, question_visibility)
VALUES
(4, 'Разрешение на оружие', 'SINGLE_CHOICE', 'Имеется ли разрешение на ношение оружия', '{9, 10}', TRUE, 'SHOW'), --10
(4, 'Владение боевыми искусствами', 'SINGLE_CHOICE', 'Владеет ли кандидат боевыми искусствами', '{9, 10}', TRUE, 'SHOW'); --11

INSERT INTO question_activate_links (layer_id, segment_id, question_id, entrypoint_id, question_required, question_visibility)
VALUES
(4, 6, 7, 1, TRUE, 'SHOW'),
(4, 6, 8, 1, TRUE, 'SHOW'),
(4, 6, 10, 1, TRUE, 'SHOW'),
(4, 6, 11, 1, TRUE, 'SHOW'),
(4, 6, 7, 2, TRUE, 'SHOW'),
(4, 6, 8, 2, TRUE, 'SHOW'),
(4, 6, 10, 2, TRUE, 'SHOW'),
(4, 6, 11, 2, TRUE, 'SHOW');

INSERT INTO history (user_id, name_db, time, type, description)
VALUES
(4, 'segment-db', '2023-04-15 02:02:00', 'UPDATE', 'Новая сессия');

--Пятый слой
INSERT INTO layers (parent_layer_id, title, description, layer_stable, layer_archive, layer_deleted, create_time)
VALUES
(3, 'Пятый слой', 'Слой, унаследованный от третьего слоя', TRUE, FALSE, FALSE, '2023-04-16 02:02:00');

INSERT INTO entrypoints (layer_id, title, description, entrypoint_type)
VALUES
(5, 'Редактирование резюме', 'Размещение резюме соискателем', 3);

INSERT INTO segments (layer_id, parent_segment_id, title, description, role, tag)
VALUES
(5, NULL, 'Водитель', 'Водитель транспортного средства', '{21}', '{"Driver", "Car", "Road"}'), --7
(5, NULL, 'Бухгатлер', 'Бухгатлер', '{3, 18}', '{"Finance", "Accounting", "Money", "Calculations"}'); --8

INSERT INTO questions (layer_id, question_title, question_type, description, possible_answers, question_required, question_visibility)
VALUES
(5, 'Доступные категории транспортных средств', 'MULTI_SELECT', 'Доступные категории транспортных средств', '{23, 24, 25, 26}', TRUE, 'SHOW'), --12
(5, 'Водительский стаж', 'SINGLE_CHOICE', 'Водительский стаж в годах', '{1, 2, 3}', TRUE, 'SHOW'), --13
(5, 'Умение работать в 1С', 'SINGLE_CHOICE', 'Умение работать в 1С', '{7, 8}', TRUE, 'SHOW'); --14

INSERT INTO answers (layer_id, open_questions, title, POSITIVE_title, answer_type, is_default_answer, skip_at_result)
VALUES
(5, NULL, 'A', 'Категория A', 'NEUTRAL', FALSE, FALSE), --23
(5, NULL, 'B', 'Категория B', 'NEUTRAL', FALSE, FALSE), --24
(5, NULL, 'C', 'Категория C', 'NEUTRAL', FALSE, FALSE), --25
(5, NULL, 'D', 'Категория D', 'NEUTRAL', FALSE, FALSE); --26

INSERT INTO question_activate_links (layer_id, segment_id, question_id, entrypoint_id, question_required, question_visibility)
VALUES
(5, 7, 12, 1, TRUE, 'SHOW'),
(5, 7, 13, 1, TRUE, 'SHOW'),
(5, 7, 12, 2, TRUE, 'SHOW'),
(5, 7, 13, 2, TRUE, 'SHOW'),
(5, 8, 1, 1, TRUE, 'SHOW'),
(5, 8, 2, 1, TRUE, 'SHOW'),
(5, 8, 14, 1, TRUE, 'SHOW'),
(5, 8, 1, 2, TRUE, 'SHOW'),
(5, 8, 2, 2, TRUE, 'SHOW'),
(5, 8, 14, 2, TRUE, 'SHOW');

INSERT INTO history (user_id, name_db, time, type, description)
VALUES
(5, 'segment-db', '2023-04-16 02:02:00', 'CREATE', 'Новая сессия');

--Шестой слой, удаленный
INSERT INTO layers (parent_layer_id, title, description, layer_stable, layer_archive, layer_deleted, create_time)
VALUES
(5, 'Шестой слой', 'Слой, унаследованный от пятого слоя, удаленный', FALSE, FALSE, TRUE, '2023-04-17 02:02:00');

INSERT INTO segments (layer_id, parent_segment_id, title, description, role, tag)
VALUES
(6, NULL, 'Механик', 'Специалист по сборке или ремонту различной техники', '{173}', '{"Repair", "Mechanic", "Assembly"}'); --9

INSERT INTO question_activate_links (layer_id, segment_id, question_id, entrypoint_id, question_required, question_visibility)
VALUES
(6, 9, 1, 1, TRUE, 'SHOW'),
(6, 9, 2, 1, TRUE, 'SHOW'),
(6, 9, 7, 1, TRUE, 'SHOW'),
(6, 9, 1, 2, TRUE, 'SHOW'),
(6, 9, 2, 2, TRUE, 'SHOW'),
(6, 9, 7, 2, TRUE, 'SHOW');

INSERT INTO history (user_id, name_db, time, type, description)
VALUES
(6, 'segment-db', '2023-04-17 02:02:00', 'DELETE', 'Новая сессия');

--Седьмой слой, экспериментальный
INSERT INTO layers (parent_layer_id, title, description, layer_stable, layer_archive, layer_deleted, create_time)
VALUES
(5, 'Седьмой слой', 'Слой, унаследованный от пятого слоя, экспериментальный', FALSE, FALSE, FALSE, '2023-04-18 02:02:00');

INSERT INTO segments (layer_id, parent_segment_id, title, description, role, tag)
VALUES
(7, NULL, 'Психолог', 'Психолог', '{101}', '{"Psychologist", "Life", "Thoughts", "Help"}'); --10

INSERT INTO questions (layer_id, question_title, question_type, description, possible_answers, question_required, question_visibility)
VALUES
(7, 'Курсы повышения квалификации', 'SINGLE_CHOICE', 'Факт прохождения курсов повышения квалификации', '{9, 10}', FALSE, 'HIDE_PREFILLED'), --15
(7, 'Возможен ли прием онлайн', 'SINGLE_CHOICE', 'Проводит ли специалист онлайн приемы пациентов', '{9, 10}', FALSE, 'HIDE'); --16

INSERT INTO question_activate_links (layer_id, segment_id, question_id, entrypoint_id, question_required, question_visibility)
VALUES
(7, 10, 1, 1, TRUE, 'SHOW'),
(7, 10, 2, 1, TRUE, 'SHOW'),
(7, 10, 15, 1, FALSE, 'HIDE_PREFILLED'),
(7, 10, 16, 1, FALSE, 'HIDE'),
(7, 10, 1, 2, TRUE, 'SHOW'),
(7, 10, 2, 2, TRUE, 'SHOW'),
(7, 10, 15, 2, FALSE, 'HIDE_PREFILLED'),
(7, 10, 16, 2, FALSE, 'HIDE');

INSERT INTO history (user_id, name_db, time, type, description)
VALUES
(7, 'segment-db', '2023-04-18 02:02:00', 'CREATE', 'Новая сессия');

