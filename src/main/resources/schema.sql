DROP TABLE IF EXISTS counter;

CREATE TABLE  counter (
  id INTEGER NOT NULL,
  counter_value INTEGER NOT NULL,
  version BIGINT NOT NULL,
  CONSTRAINT pk_counter primary key (id)
);

DROP TABLE IF EXISTS post;

CREATE TABLE post (
    id BIGINT PRIMARY KEY,
    title VARCHAR(255),
    body TEXT,
    status VARCHAR(50)
);
