CREATE DATABASE jotesdb;

\c jotesdb;

CREATE TABLE IF NOT EXISTS category
(
  id    SERIAL  PRIMARY KEY,
  txt   VARCHAR NOT NULL,
  color VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS note
(
  id          SERIAL  PRIMARY KEY,
  category_id INTEGER NOT NULL,
  txt         VARCHAR NOT NULL,

  FOREIGN KEY (category_id) REFERENCES category(id)
);
