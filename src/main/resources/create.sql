CREATE TABLE userlist
(
  telegram_id INTEGER     NOT NULL UNIQUE ,
  first_name  VARCHAR(20) NOT NULL,
  last_name   VARCHAR(20),
  username    VARCHAR(20)
);

CREATE TABLE request_history
(
  telegram_id INTEGER      NOT NULL UNIQUE,
  url         VARCHAR(100) NOT NULL,
  title       VARCHAR(50)  NOT NULL,
  user_id     INTEGER      NOT NULL REFERENCES userlist(telegram_id)
);







