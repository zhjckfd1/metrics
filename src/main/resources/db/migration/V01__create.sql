create table IF NOT EXISTS test_table
(
    id           BIGSERIAL PRIMARY KEY,
    created_on   timestamp,
    updated_on   timestamp,
    text         varchar(255)
);