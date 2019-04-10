CREATE TABLE shedlock
(
  name       VARCHAR(64)  NOT NULL,
  lock_until DATETIME     NULL,
  locked_at  DATETIME     NULL,
  locked_by  VARCHAR(255) NOT NULL,
  PRIMARY KEY (name)
);
