ALTER TABLE game DROP CONSTRAINT game_status_check;

ALTER TABLE game
    ADD CONSTRAINT game_status_check
    CHECK (status IN ('complete', 'in_progress', 'inactive', 'unknown'));