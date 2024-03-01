DROP TABLE IF EXISTS counter;

CREATE TABLE  counter (
  id INTEGER NOT NULL,
  counter_value INTEGER NOT NULL,
  version BIGINT NOT NULL,
  CONSTRAINT pk_counter primary key (id)
);