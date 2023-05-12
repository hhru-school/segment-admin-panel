DROP TABLE IF EXISTS platforms CASCADE;
DROP TABLE IF EXISTS applications CASCADE;
DROP TABLE IF EXISTS layers CASCADE;
DROP TABLE IF EXISTS entrypoints CASCADE;
DROP TABLE IF EXISTS segments CASCADE;
DROP TABLE IF EXISTS questions CASCADE;
DROP TABLE IF EXISTS answers CASCADE;
DROP TABLE IF EXISTS screens CASCADE;
DROP TABLE IF EXISTS segment_application_screen_links CASCADE;
DROP TABLE IF EXISTS question_required_links CASCADE;
DROP TABLE IF EXISTS screen_question_links CASCADE;
DROP TABLE IF EXISTS screen_applications CASCADE;
DROP TABLE IF EXISTS history CASCADE;

CREATE TABLE IF NOT EXISTS platforms
(
    platform_id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    platform            VARCHAR(255),
    application_version VARCHAR(255) NOT NULL
);
COMMENT ON COLUMN platforms.platform IS 'enum (ANDROID, IOS, WEB)';


CREATE TABLE IF NOT EXISTS applications
(
    application_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title          VARCHAR(255) NOT NULL,
    description    VARCHAR(255) NOT NULL,
    platforms      BIGINT[],
    state          VARCHAR(255)
);
COMMENT ON COLUMN applications.state IS 'enum (ARCHIVE, ACTIVE)';


CREATE TABLE IF NOT EXISTS layers
(
    layer_id        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    parent_layer_id BIGINT REFERENCES layers (layer_id),
    title           VARCHAR(255)             NOT NULL,
    description     VARCHAR(255),
    state           VARCHAR(255),
    create_time     TIMESTAMP WITH TIME ZONE NOT NULL
);
COMMENT ON COLUMN layers.state IS 'enum (STABLE, ARCHIVE, TEST)';


CREATE TABLE IF NOT EXISTS entrypoints
(
    entrypoint_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title         VARCHAR(255) NOT NULL,
    description   VARCHAR(255)
);


CREATE TABLE IF NOT EXISTS segments
(
    segment_id        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    layer_id          BIGINT REFERENCES layers (layer_id),
    parent_segment_id BIGINT REFERENCES segments (segment_id),
    old_segment_id    BIGINT REFERENCES segments (segment_id),
    title             VARCHAR(255) NOT NULL,
    description       VARCHAR(255),
    role              BIGINT[],
    tag               VARCHAR(255)[],
    state             VARCHAR(255)
);
COMMENT ON COLUMN segments.state IS 'enum (ARCHIVE, ACTIVE)';


CREATE TABLE IF NOT EXISTS questions
(
    question_id      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title            VARCHAR(255) NOT NULL,
    description      VARCHAR(255),
    type             VARCHAR(255) NOT NULL,
    answer_type      VARCHAR(255) NOT NULL,
    possible_answers BIGINT[]
);
COMMENT ON COLUMN questions.type IS 'enum (RESUME_FIELD, QUESTION)';
COMMENT ON COLUMN questions.answer_type IS 'enum (SINGLE_CHOICE, MULTI_SELECT,  NONE)';


CREATE TABLE IF NOT EXISTS answers
(
    answer_id      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    open_questions BIGINT[],
    title          VARCHAR(255),
    positive_title VARCHAR(255),
    type           VARCHAR(255),
    default_answer BOOLEAN NOT NULL,
    skip_at_result BOOLEAN
);
COMMENT ON COLUMN answers.type IS 'enum (POSITIVE,  NEGATIVE,  NEUTRAL)';


CREATE TABLE IF NOT EXISTS screens
(
    screen_id   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title       VARCHAR(255),
    description VARCHAR(255),
    type        VARCHAR(255),
    state       VARCHAR(255)
);
COMMENT ON COLUMN screens.type IS 'enum (STATIC, DYNAMIC)';
COMMENT ON COLUMN screens.state IS 'enum (ACTIVE, ARCHIVE)';


CREATE TABLE IF NOT EXISTS segment_application_screen_links
(
    id              BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    layer_id        BIGINT REFERENCES layers (layer_id),
    old_id          BIGINT REFERENCES segment_application_screen_links (id),
    segment_id      BIGINT REFERENCES segments (segment_id),
    entrypoint_id   BIGINT REFERENCES entrypoints (entrypoint_id),
    application_id  BIGINT REFERENCES applications (application_id),
    screen_id       BIGINT REFERENCES screens (screen_id),
    screen_position INT
);


CREATE TABLE IF NOT EXISTS screen_question_links
(
    id                  BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    layer_id            BIGINT REFERENCES layers (layer_id),
    old_id              BIGINT REFERENCES screen_question_links (id),
    segment_id          BIGINT REFERENCES segments (segment_id),
    entrypoint_id       BIGINT REFERENCES entrypoints (entrypoint_id),
    application_id      BIGINT REFERENCES applications (application_id),
    screen_id           BIGINT REFERENCES screens (screen_id),
    question_id         BIGINT REFERENCES questions (question_id),
    question_position   INT,
    question_visibility VARCHAR(255)
);
COMMENT ON COLUMN screen_question_links.question_visibility IS 'enum (SHOW,  HIDE,  HIDE_PREFILLED)';


CREATE TABLE IF NOT EXISTS question_required_links
(
    id                BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    layer_id          BIGINT REFERENCES layers (layer_id),
    old_id            BIGINT REFERENCES question_required_links (id),
    segment_id        BIGINT REFERENCES segments (segment_id),
    application_id    BIGINT REFERENCES applications (application_id),
    question_id       BIGINT REFERENCES questions (question_id),
    question_required BOOLEAN
);


CREATE TABLE IF NOT EXISTS screen_applications
(
    screen_id      BIGINT REFERENCES screens (screen_id),
    application_id BIGINT REFERENCES applications (application_id),
    PRIMARY KEY (screen_id, application_id)
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
COMMENT ON COLUMN history.type IS 'enum (CREATE, UPDATE, DELETE)';
