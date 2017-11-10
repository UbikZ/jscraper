-- * cc   : constraint check
-- * ucc  : unique constraint check
-- * fk   : foreign key

CREATE TABLE IF NOT EXISTS public.user
(
  id            SERIAL PRIMARY KEY      NOT NULL,
  username      VARCHAR(255)            NOT NULL,
  firstname     VARCHAR(255),
  lastname      VARCHAR(255),
  password      VARCHAR(255),
  email         VARCHAR(255),
  date          TIMESTAMP DEFAULT NOW() NOT NULL,
  is_admin      BOOLEAN DEFAULT FALSE   NOT NULL,
  enabled       BOOLEAN DEFAULT TRUE    NOT NULL
);
CREATE INDEX IF NOT EXISTS user_date_idx
  ON public.user (date);
CREATE UNIQUE INDEX IF NOT EXISTS user_username_uidx
  ON public.user (username);
CREATE UNIQUE INDEX IF NOT EXISTS user_email_uidx
  ON public.user (email);