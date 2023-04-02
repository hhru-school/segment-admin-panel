DROP TABLE IF EXISTS answers CASCADE;
DROP TABLE IF EXISTS questions CASCADE;
DROP TABLE IF EXISTS commits CASCADE;
DROP TABLE IF EXISTS segments CASCADE;
DROP TABLE IF EXISTS segment_entrypoint CASCADE;
DROP TABLE IF EXISTS segments_questions CASCADE;
DROP TABLE IF EXISTS history CASCADE;


CREATE TABLE IF NOT EXISTS commits
(
    id               BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    parent_commit_id BIGINT REFERENCES commits (id),
    name             VARCHAR(128) NOT NULL,
    description      TEXT,
    stable           BOOLEAN,
    deleted          BOOLEAN,
    created_time     TIMESTAMP
);

CREATE TABLE IF NOT EXISTS segment_entrypoint
(
    id              BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    commit_id       BIGINT REFERENCES commits (id),
    name            VARCHAR(128) NOT NULL,
    description     TEXT,
    type_entrypoint VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS segments
(
    id                    BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    commit_id             BIGINT REFERENCES commits (id),
    segment_entrypoint_id BIGINT REFERENCES segment_entrypoint (id),
    parent_segment_id     BIGINT REFERENCES segments (id),
    role_id               BIGINT,
    key_value             VARCHAR(128),
    name                  VARCHAR(128) NOT NULL,
    description           TEXT
);

CREATE TABLE IF NOT EXISTS questions
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    commit_id   BIGINT REFERENCES commits (id),
    name        VARCHAR(128) NOT NULL,
    description TEXT,
    type_answer VARCHAR(15),
    active      BOOLEAN,
    required    BOOLEAN
);

CREATE TABLE IF NOT EXISTS answers
(
    id               BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    commit_id        BIGINT REFERENCES commits (id),
    question_id      BIGINT REFERENCES questions (id),
    question_next_id BIGINT REFERENCES questions (id),
    name             VARCHAR(128),
    description      TEXT,
    checked          BOOLEAN
);

CREATE TABLE IF NOT EXISTS question_activate_links
(
    id                    BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    commit_id             BIGINT REFERENCES commits (id),
    segment_id            BIGINT REFERENCES commits (id),
    question_id           BIGINT REFERENCES questions (id),
    segment_entrypoint_id BIGINT REFERENCES segment_entrypoint (id),
    active                BOOLEAN
);

CREATE TABLE IF NOT EXISTS history
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id     BIGINT,
    name_db     VARCHAR(20),
    time        TIMESTAMP,
    type        VARCHAR(10),
    description TEXT
);
