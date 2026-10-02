CREATE TABLE repo_commit (
    id BIGSERIAL PRIMARY KEY,
    project_id BIGINT NOT NULL REFERENCES project(id),
    commit_sha CHAR(40) NOT NULL,
    committed_at TIMESTAMPTZ NOT NULL,
    UNIQUE (project_id, commit_sha)
);

CREATE INDEX idx_repo_commit_project_date
    ON repo_commit (project_id, committed_at);