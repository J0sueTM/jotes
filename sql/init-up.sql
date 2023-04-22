CREATE TABLE _user
(
  id    SERIAL  PRIMARY KEY,
  email VARCHAR NOT NULL UNIQUE,
  hash  VARCHAR NOT NULL
);

CREATE TABLE category
(
  id    SERIAL  PRIMARY KEY,
  txt   VARCHAR NOT NULL,
  color VARCHAR NOT NULL
);

CREATE TABLE user_category
(
  id SERIAL PRIMARY KEY,
  user_id INTEGER NOT NULL,
  category_id INTEGER NOT NULL,

  FOREIGN KEY (user_id)
          REFERENCES _user(id),
  FOREIGN KEY (category_id)
          REFERENCES category(id)
);

CREATE TABLE note
(
  id          SERIAL  PRIMARY KEY,
  user_id     INTEGER NOT NULL,
  category_id INTEGER NOT NULL,
  txt         VARCHAR NOT NULL,

  FOREIGN KEY (user_id)
          REFERENCES _user(id),
  FOREIGN KEY (category_id)
          REFERENCES category(id)
);
