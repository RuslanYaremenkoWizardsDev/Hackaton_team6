CREATE DATABASE bruh WITH OWNER postgres;

ALTER USER postgres WITH PASSWORD 'root';

CREATE SEQUENCE hibernate_sequence START 1 INCREMENT 1;

CREATE TABLE user_credentials
(
    id       int4         not null,
    login    varchar(255) not null,
    password varchar(255) not null,
    primary key (id)
);

ALTER TABLE user_credentials DROP CONSTRAINT uk_login;
ALTER TABLE user_credentials ADD CONSTRAINT uk_login UNIQUE (login);