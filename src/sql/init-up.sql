CREATE TABLE category
(
  id    SERIAL  PRIMARY KEY,
  txt   VARCHAR NOT NULL,
  color VARCHAR NOT NULL
);

CREATE TABLE note
(
  id          SERIAL  PRIMARY KEY,
  category_id INTEGER NOT NULL,
  txt         VARCHAR NOT NULL,

  FOREIGN KEY (category_id) REFERENCES category(id)
);
