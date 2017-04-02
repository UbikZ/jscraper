-- * cc   : constraint check
-- * ucc  : unique constraint check
-- * fk   : foreign key

CREATE TABLE IF NOT EXISTS public.feed_type
(
  id            SERIAL PRIMARY KEY      NOT NULL,
  label         VARCHAR(255),
  date          TIMESTAMP DEFAULT NOW() NOT NULL,
  url_regex     TEXT                    NOT NULL,
  content_regex TEXT DEFAULT ''         NOT NULL,
  enabled       BOOLEAN DEFAULT TRUE    NOT NULL
);
CREATE INDEX IF NOT EXISTS feedType_date_idx
  ON public.feed_type (date);
CREATE UNIQUE INDEX IF NOT EXISTS feedType_label_uidx
  ON public.feed_type (label);

CREATE TABLE IF NOT EXISTS public.feed
(
  id           SERIAL PRIMARY KEY      NOT NULL,
  label        VARCHAR(255),
  date         TIMESTAMP DEFAULT NOW() NOT NULL,
  url          TEXT                    NOT NULL,
  enabled      BOOLEAN DEFAULT TRUE    NOT NULL,
  feed_type_id INT                     NOT NULL,
  CONSTRAINT feed_feedTypeId_fk FOREIGN KEY (feed_type_id) REFERENCES feed_type (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT feed_url_cc CHECK (url ~*
                                '^(?:http(s)?:\/\/)?[\w.-]+(?:\.[\w\.-]+)+[\w\-\._~:/?#[\]@!\$&''\(\)\*\+,;=.]+$')
);
CREATE INDEX IF NOT EXISTS feed_date_idx
  ON public.feed (date);
CREATE UNIQUE INDEX IF NOT EXISTS feed_label_uidx
  ON public.feed (label);
CREATE UNIQUE INDEX IF NOT EXISTS feed_url_uidx
  ON public.feed (url);


CREATE TABLE IF NOT EXISTS public.feed_item
(
  id       SERIAL PRIMARY KEY      NOT NULL,
  label    VARCHAR(255)            NOT NULL,
  url      TEXT                    NOT NULL,
  comment  TEXT                    NULL,
  date     TIMESTAMP DEFAULT NOW() NOT NULL,
  checksum VARCHAR(255)            NOT NULL,
  viewed   BOOLEAN DEFAULT FALSE   NOT NULL,
  approved BOOLEAN DEFAULT FALSE   NOT NULL,
  reposted BOOLEAN DEFAULT FALSE   NOT NULL,
  sent     BOOLEAN DEFAULT FALSE   NOT NULL,
  enabled  BOOLEAN DEFAULT TRUE    NOT NULL,
  feed_id  INT                     NOT NULL,
  CONSTRAINT feedItem_url_cc CHECK (url ~*
                                    '^(?:http(s)?:\/\/)?[\w.-]+(?:\.[\w\.-]+)+[\w\-\._~:/?#[\]@!\$&''\(\)\*\+,;=.]+$'),
  CONSTRAINT feedItem_feedId_fk FOREIGN KEY (feed_id) REFERENCES feed (id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE INDEX IF NOT EXISTS feedItem_date_idx
  ON public.feed_item (date);
CREATE UNIQUE INDEX IF NOT EXISTS feedItem_url_uidx
  ON public.feed_item (url);
CREATE UNIQUE INDEX IF NOT EXISTS feedItem_label_uidx
  ON public.feed_item (label);
CREATE UNIQUE INDEX IF NOT EXISTS feedItem_checksum_uidx
  ON public.feed_item (checksum);

CREATE TABLE IF NOT EXISTS public.tag
(
  id      SERIAL PRIMARY KEY      NOT NULL,
  label   VARCHAR(255)            NOT NULL,
  date    TIMESTAMP DEFAULT NOW() NOT NULL,
  enabled BOOLEAN DEFAULT TRUE    NOT NULL
);
CREATE UNIQUE INDEX IF NOT EXISTS tag_label_uidx
  ON public.tag (label);
CREATE INDEX IF NOT EXISTS tag_date_idx
  ON public.tag (date);

CREATE TABLE IF NOT EXISTS public.feed_item_tag
(
  id           SERIAL PRIMARY KEY      NOT NULL,
  feed_item_id INT                     NOT NULL,
  tag_id       INT                     NOT NULL,
  CONSTRAINT feedItemTag_feedItemId_fk FOREIGN KEY (feed_item_id) REFERENCES feed_item (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT feedItemTag_tagId_fk FOREIGN KEY (tag_id) REFERENCES tag (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT feedItemTag_feedItemId_tagId_ucc UNIQUE (feed_item_id, tag_id)
);

CREATE TABLE IF NOT EXISTS public.tag_prohibited
(
  id      SERIAL PRIMARY KEY      NOT NULL,
  label   VARCHAR(255)            NOT NULL,
  enabled BOOLEAN DEFAULT TRUE    NOT NULL
);
CREATE UNIQUE INDEX IF NOT EXISTS tagProhibited_label_uidx
  ON public.tag_prohibited (label);

CREATE TABLE IF NOT EXISTS public.feed_prohibited
(
  id      SERIAL PRIMARY KEY      NOT NULL,
  label   VARCHAR(255)            NOT NULL,
  enabled BOOLEAN DEFAULT TRUE    NOT NULL
);
CREATE UNIQUE INDEX IF NOT EXISTS feedProhibited_label_uidx
  ON public.feed_prohibited (label);
