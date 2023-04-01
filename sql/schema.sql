DROP TABLE IF EXISTS answers CASCADE;
DROP TABLE IF EXISTS questions CASCADE;
DROP TABLE IF EXISTS branches CASCADE;
DROP TABLE IF EXISTS segments CASCADE;
DROP TABLE IF EXISTS segment_owner CASCADE;
DROP TABLE IF EXISTS history CASCADE;

CREATE TABLE IF NOT EXISTS segment_owner
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name        VARCHAR(128),
    type_owner  VARCHAR(15),
    description TEXT
);

CREATE TABLE IF NOT EXISTS segments
(
    id                BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name              VARCHAR(128),
    description       TEXT,
    segment_owner_id  BIGINT REFERENCES segment_owner (id),
    parent_segment_id BIGINT REFERENCES segments (id),
    role_id           BIGINT,
    key_value         VARCHAR(128)
);


CREATE TABLE IF NOT EXISTS branches
(
    id               BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    parent_branch_id BIGINT REFERENCES branches (id),
    segment_id       BIGINT REFERENCES segments (id),
    name             VARCHAR(128),
    description      TEXT,
    stable           BOOLEAN,
    deleted          BOOLEAN,
    created_time     TIMESTAMP
);

CREATE TABLE IF NOT EXISTS questions
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name        VARCHAR(128),
    description TEXT,
    branch_id   BIGINT REFERENCES branches (id),
    type_answer VARCHAR(15),
    active      BOOLEAN,
    required    BOOLEAN
);

CREATE TABLE IF NOT EXISTS answers
(
    id               BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name             VARCHAR(128),
    description      TEXT,
    question_id      BIGINT REFERENCES questions (id),
    question_next_id BIGINT REFERENCES questions (id),
    checked          BOOLEAN
);

CREATE TABLE IF NOT EXISTS history
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    type        VARCHAR(10),
    name_db     VARCHAR(20),
    user_id     BIGINT,
    description TEXT,
    time        TIMESTAMP
);
