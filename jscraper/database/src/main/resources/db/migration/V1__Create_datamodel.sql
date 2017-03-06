-- * cc   : constraint check
-- * ucc  : unique constraint check
-- * fk   : foreign key

CREATE TABLE public.feed_type
(
  id            SERIAL PRIMARY KEY      NOT NULL,
  label         VARCHAR(255),
  date          TIMESTAMP DEFAULT NOW() NOT NULL,
  url_regex     TEXT                    NOT NULL,
  content_regex TEXT DEFAULT ''         NOT NULL,
  enabled       BOOLEAN DEFAULT TRUE    NOT NULL
);
CREATE INDEX feedType_label_idx
  ON public.feed_type (label);

CREATE TABLE public.feed
(
  id           SERIAL PRIMARY KEY      NOT NULL,
  label        VARCHAR(255),
  date         TIMESTAMP DEFAULT NOW() NOT NULL,
  url          TEXT                    NOT NULL,
  enabled      BOOLEAN DEFAULT TRUE    NOT NULL,
  feed_type_id INT                     NOT NULL,
  CONSTRAINT feedItem_feedTypeId_fk FOREIGN KEY (feed_type_id) REFERENCES feed_type (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT feed_url_cc CHECK (url ~*
                                '^(?:http(s)?:\/\/)?[\w.-]+(?:\.[\w\.-]+)+[\w\-\._~:/?#[\]@!\$&''\(\)\*\+,;=.]+$'),
  CONSTRAINT feed_url_ucc UNIQUE (url),
  CONSTRAINT feed_label_ucc UNIQUE (label)
);
CREATE INDEX feed_label_idx
  ON public.feed (label);

CREATE TABLE public.feed_item
(
  id       SERIAL PRIMARY KEY      NOT NULL,
  label    VARCHAR(255)            NOT NULL,
  url      TEXT                    NOT NULL,
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
  CONSTRAINT feedItem_feedId_fk FOREIGN KEY (feed_id) REFERENCES feed (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT feedItem_url_ucc UNIQUE (url),
  CONSTRAINT feedItem_label_ucc UNIQUE (label),
  CONSTRAINT feedItem_checksum_ucc UNIQUE (checksum)
);
CREATE INDEX feedItem_label_idx
  ON public.feed_item (label);

CREATE TABLE public.tag
(
  id      SERIAL PRIMARY KEY      NOT NULL,
  label   VARCHAR(255)            NOT NULL,
  date    TIMESTAMP DEFAULT NOW() NOT NULL,
  enabled BOOLEAN DEFAULT TRUE    NOT NULL,
  CONSTRAINT tag_label_ucc UNIQUE (label)
);
CREATE INDEX tag_label_idx
  ON public.tag (label);

CREATE TABLE public.feed_item_tag
(
  id           SERIAL PRIMARY KEY      NOT NULL,
  feed_item_id INT                     NOT NULL,
  tag_id       INT                     NOT NULL,
  CONSTRAINT feedItemTag_feedItemId_fk FOREIGN KEY (feed_item_id) REFERENCES feed_item (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT feedItemTag_tagId_fk FOREIGN KEY (tag_id) REFERENCES tag (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT feedItemTag_feedItemId_tagId_ucc UNIQUE (feed_item_id, tag_id)
);

CREATE TABLE public.tag_prohibited
(
  id      SERIAL PRIMARY KEY      NOT NULL,
  label   VARCHAR(255)            NOT NULL,
  enabled BOOLEAN DEFAULT TRUE    NOT NULL,
  CONSTRAINT tagProhibited_label_ucc UNIQUE (label)
);
CREATE INDEX tagProhibited_label_idx
  ON public.tag_prohibited (label);

CREATE TABLE public.feed_prohibited
(
  id      SERIAL PRIMARY KEY      NOT NULL,
  label   VARCHAR(255)            NOT NULL,
  enabled BOOLEAN DEFAULT TRUE    NOT NULL,
  CONSTRAINT feedProhibited_label_ucc UNIQUE (label)
);
CREATE INDEX feedProhibited_label_idx
  ON public.feed_prohibited (label);
