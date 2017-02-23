-- * cc   : constraint check
-- * ucc  : unique constraint check
-- * fk   : foreign key

CREATE TABLE public.feed
(
  id      SERIAL PRIMARY KEY      NOT NULL,
  label   VARCHAR(255),
  date    TIMESTAMP DEFAULT NOW() NOT NULL,
  url     TEXT                    NOT NULL,
  enabled BOOLEAN DEFAULT TRUE    NOT NULL,
  CONSTRAINT feed_url_cc CHECK (url ~*
                                '^(?:http(s)?:\/\/)?[\w.-]+(?:\.[\w\.-]+)+[\w\-\._~:/?#[\]@!\$&''\(\)\*\+,;=.]+$'),
  CONSTRAINT feed_url_ucc UNIQUE (url)
);

CREATE TABLE public.feed_type
(
  id            SERIAL PRIMARY KEY      NOT NULL,
  label         VARCHAR(255),
  date          TIMESTAMP DEFAULT NOW() NOT NULL,
  url_regex     TEXT                    NOT NULL,
  content_regex TEXT                    NOT NULL,
  enabled       BOOLEAN DEFAULT TRUE    NOT NULL
);

CREATE TABLE public.feed_item
(
  id           SERIAL PRIMARY KEY      NOT NULL,
  label        VARCHAR(255)            NOT NULL,
  url          TEXT                    NOT NULL,
  date         TIMESTAMP DEFAULT NOW() NOT NULL,
  checksum     VARCHAR(255)            NOT NULL,
  viewed       BOOLEAN DEFAULT FALSE   NOT NULL,
  approved     BOOLEAN DEFAULT FALSE   NOT NULL,
  repposted    BOOLEAN DEFAULT FALSE   NOT NULL,
  sent         BOOLEAN DEFAULT FALSE   NOT NULL,
  enabled      BOOLEAN DEFAULT TRUE    NOT NULL,
  feed_id      INT                     NOT NULL,
  feed_type_id INT                     NOT NULL,
  CONSTRAINT feedItem_url_cc CHECK (url ~*
                                    '^(?:http(s)?:\/\/)?[\w.-]+(?:\.[\w\.-]+)+[\w\-\._~:/?#[\]@!\$&''\(\)\*\+,;=.]+$'),
  CONSTRAINT feedItem_feedTypeId_fk FOREIGN KEY (feed_type_id) REFERENCES feed_type (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT feedItem_feedId_fk FOREIGN KEY (feed_id) REFERENCES feed (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT feedItem_url_ucc UNIQUE (url),
  CONSTRAINT feedItem_checksum_ucc UNIQUE (checksum)
);

CREATE TABLE public.tag
(
  id    SERIAL PRIMARY KEY      NOT NULL,
  label VARCHAR(255)            NOT NULL,
  date  TIMESTAMP DEFAULT NOW() NOT NULL
);

CREATE TABLE public.feed_item_tag
(
  id           SERIAL PRIMARY KEY      NOT NULL,
  feed_item_id INT                     NOT NULL,
  tag_id       INT                     NOT NULL,
  date         TIMESTAMP DEFAULT NOW() NOT NULL,
  CONSTRAINT feedItemTag_feedItemId_fk FOREIGN KEY (feed_item_id) REFERENCES feed_item (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT feedItemTag_tagId_fk FOREIGN KEY (tag_id) REFERENCES tag (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT feedItemTag_feedItemId_tagId_ucc UNIQUE (feed_item_id, tag_id)
);
