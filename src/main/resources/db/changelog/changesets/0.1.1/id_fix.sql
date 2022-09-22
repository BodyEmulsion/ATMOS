ALTER TABLE users
    ALTER COLUMN id TYPE bigint;
ALTER TABLE settings
    ALTER COLUMN id TYPE bigint;
ALTER TABLE projects
    ALTER COLUMN id TYPE bigint;
ALTER TABLE blocks
    ALTER COLUMN id TYPE bigint;
ALTER TABLE tasks
    ALTER COLUMN id TYPE bigint;
ALTER TABLE notifications
    ALTER COLUMN id TYPE bigint;
ALTER TABLE settings
    ALTER COLUMN user_id TYPE bigint;
ALTER TABLE projects
    ALTER COLUMN user_id TYPE bigint;
ALTER TABLE blocks
    ALTER COLUMN project_id TYPE bigint;
ALTER TABLE tasks
    ALTER COLUMN project_id TYPE bigint;
ALTER TABLE tasks
    ALTER COLUMN block_id TYPE bigint;
ALTER TABLE notifications
    ALTER COLUMN task_id TYPE bigint;
