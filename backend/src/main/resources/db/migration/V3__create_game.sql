CREATE TABLE game(
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(150) NOT NULL UNIQUE,
    repo_url VARCHAR(255),
    status VARCHAR(20) NOT NULL,
    progress_percent NUMERIC(5, 2),
    progress_metric VARCHAR(40) NOT NULL,
    matched_functions INTEGER,
    total_functions INTEGER,
    progress_note VARCHAR(1000),
    source_url VARCHAR(255),
    checked_on DATE,
    CONSTRAINT game_status_check CHECK (status IN ('complete', ', in_progress', 'inactive', 'unknown'))
)