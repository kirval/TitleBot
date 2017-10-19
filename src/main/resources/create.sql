CREATE TABLE request_history
(
  id          SERIAL       NOT NULL
    CONSTRAINT request_history_pkey
    PRIMARY KEY,
  telegram_id INTEGER      NOT NULL,
  url         VARCHAR(100) NOT NULL,]
  title       VARCHAR(50)  NOT NULL
);

CREATE TABLE userlist
(
  id          SERIAL      NOT NULL
    CONSTRAINT userlist_pkey
    PRIMARY KEY,
  telegram_id INTEGER     NOT NULL,
  first_name  VARCHAR(20) NOT NULL,
  last_name   VARCHAR(20),
  username    VARCHAR(20)
);





