--liquibase formatted sql
--changeset nvoxland:1
CREATE TABLE IF NOT EXISTS links
(
    url_id bigint NOT NULL PRIMARY KEY,
    url varchar(256) NOT NULL UNIQUE
);

--changeset nvoxland:1
CREATE TABLE IF NOT EXISTS users
(
    user_id bigint NOT NULL PRIMARY KEY,
    username varchar(32)
);

--changeset nvoxland:1
CREATE TABLE IF NOT EXISTS users_and_links
(
    link_id bigint NOT NULL PRIMARY KEY,
    chat_id bigint NOT NULL PRIMARY KEY,
    FOREIGN KEY (link_id) REFERENCES links (url_id),
    FOREIGN KEY (chat_id) REFERENCES users (user_id)
);