CREATE TABLE project (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    repo_url VARCHAR(255) NOT NULL
);

CREATE TABLE snapshot (
    id BIGSERIAL PRIMARY KEY,
    project_id BIGINT NOT NULL REFERENCES project(id),
    commit_sha CHAR(40) NOT NULL,
    committed_at TIMESTAMPTZ NOT NULL,
    matched_functions INTEGER NOT NULL,
    total_functions INTEGER NOT NULL,
    UNIQUE (project_id, commit_sha)
);