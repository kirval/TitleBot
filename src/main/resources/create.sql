CREATE TABLE userlist
(
  id SERIAL NOT NULL PRIMARY KEY ,
  telegram_id INTEGER     NOT NULL UNIQUE ,
  first_name  VARCHAR(20) NOT NULL,
  last_name   VARCHAR(20),
  username    VARCHAR(20)
);

CREATE TABLE request_history
(
  id SERIAL NOT NULL PRIMARY KEY ,
  user_telegram_id INTEGER      NOT NULL REFERENCES userlist(telegram_id),
  url         VARCHAR(200) NOT NULL,
  title       VARCHAR(200)  NOT NULL
);

CREATE TABLE selector
(
  id SERIAL NOT NULL PRIMARY KEY ,
  telegram_id INTEGER NOT NULL REFERENCES userlist(telegram_id),
  url VARCHAR(550) NOT NULL
);

CREATE TABLE orders
(
  id SERIAL NOT NULL PRIMARY KEY ,
  order_link VARCHAR(550) NOT NULL ,
  selector_id SERIAL NOT NULL REFERENCES selector(id)
);









