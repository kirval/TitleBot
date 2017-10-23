CREATE TABLE userlist
(
  id SERIAL NOT NULL ,
  telegram_id INTEGER     NOT NULL UNIQUE ,
  first_name  VARCHAR(20) NOT NULL,
  last_name   VARCHAR(20),
  username    VARCHAR(20)
);

CREATE TABLE request_history
(
  id SERIAL NOT NULL ,
  user_telegram_id INTEGER      NOT NULL REFERENCES userlist(telegram_id),
  url         VARCHAR(200) NOT NULL,
  title       VARCHAR(200)  NOT NULL
);







