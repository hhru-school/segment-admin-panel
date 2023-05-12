DROP TABLE IF EXISTS answers CASCADE;
DROP TABLE IF EXISTS questions CASCADE;
DROP TABLE IF EXISTS segments CASCADE;
DROP TABLE IF EXISTS entrypoints CASCADE;
DROP TABLE IF EXISTS question_activate_links CASCADE;
DROP TABLE IF EXISTS layers CASCADE;
DROP TABLE IF EXISTS history CASCADE;

CREATE TABLE IF NOT EXISTS layers
(
    layer_id        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    parent_layer_id BIGINT REFERENCES layers (layer_id),
    title           VARCHAR(255)             NOT NULL,
    description     VARCHAR(255),
    layer_stable    BOOLEAN,
    layer_archive   BOOLEAN,
    layer_deleted   BOOLEAN,
    create_time     TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TABLE IF NOT EXISTS entrypoints
(
    entrypoint_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    layer_id      BIGINT REFERENCES layers (layer_id),
    title         VARCHAR(255) NOT NULL,
    description   VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS segments
(
    segment_id        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    layer_id          BIGINT REFERENCES layers (layer_id),
    parent_segment_id BIGINT REFERENCES segments (segment_id),
    title             VARCHAR(255) NOT NULL,
    description       VARCHAR(255),
    role              BIGINT[],
    tag               VARCHAR(255)[],
    archived          BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS questions
(
    question_id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    layer_id            BIGINT REFERENCES layers (layer_id),
    question_title      VARCHAR(255) NOT NULL,
    question_type       VARCHAR(255) NOT NULL,
    description         VARCHAR(255),
    possible_answers    BIGINT[],
    question_required   BOOLEAN,
    question_visibility VARCHAR(255),
    resume_field        BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS answers
(
    answer_id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    layer_id          BIGINT REFERENCES layers (layer_id),
    open_questions    BIGINT[],
    title             VARCHAR(255),
    positive_title    VARCHAR(255),
    answer_type       VARCHAR(255),
    is_default_answer BOOLEAN NOT NULL,
    skip_at_result    BOOLEAN
);

CREATE TABLE IF NOT EXISTS question_activate_links
(
    id                  BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    layer_id            BIGINT REFERENCES layers (layer_id),
    segment_id          BIGINT REFERENCES segments (segment_id),
    question_id         BIGINT REFERENCES questions (question_id),
    entrypoint_id       BIGINT REFERENCES entrypoints (entrypoint_id),
    question_required   BOOLEAN,
    question_visibility VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS history
(
    history_id  BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id     BIGINT,
    name_db     VARCHAR(255),
    time        TIMESTAMP WITH TIME ZONE NOT NULL,
    type        VARCHAR(255),
    description TEXT
);
