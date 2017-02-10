CREATE TABLE public.feed
(
  id SERIAL PRIMARY KEY NOT NULL,
  ref_key VARCHAR(255) NOT NULL,
  label VARCHAR(255),
  date TIMESTAMP DEFAULT NOW() NOT NULL,
  url TEXT NOT NULL,
  enabled BIT DEFAULT b'1'  NOT NULL,
  CONSTRAINT feed_url_constraint_check CHECK (url ~* '^(?:http(s)?:\/\/)?[\w.-]+(?:\.[\w\.-]+)+[\w\-\._~:/?#[\]@!\$&''\(\)\*\+,;=.]+$')
);
CREATE UNIQUE INDEX feed_id_uindex ON public.feed (id);
CREATE UNIQUE INDEX feed_ref_key_uindex ON public.feed (ref_key);
CREATE UNIQUE INDEX feed_url_uindex ON public.feed (url);

CREATE TABLE public.feed_type
(
  id SERIAL PRIMARY KEY NOT NULL,
  ref_key VARCHAR(255) NOT NULL,
  label VARCHAR(255),
  date TIMESTAMP DEFAULT NOW() NOT NULL,
  url_regex TEXT NOT NULL,
  content_regex TEXT NOT NULL,
  enabled BIT DEFAULT b'1' NOT NULL
);
CREATE UNIQUE INDEX feed_type_id_uindex ON public.feed_type (id);
CREATE UNIQUE INDEX feed_type_ref_key_uindex ON public.feed_type (ref_key);

CREATE TABLE public.feed_item
(
  id SERIAL PRIMARY KEY NOT NULL,
  label VARCHAR(255) NOT NULL,
  url TEXT NOT NULL,
  date TIMESTAMP DEFAULT NOW() NOT NULL,
  checksum VARCHAR(255) NOT NULL,
  viewed BIT DEFAULT b'0' NOT NULL,
  approved BIT DEFAULT b'0' NOT NULL,
  repposted BIT DEFAULT b'0' NOT NULL,
  sent BIT DEFAULT b'0' NOT NULL,
  enabled BIT DEFAULT b'1' NOT NULL,
  feed_id INT NOT NULL,
  feed_type_id INT NOT NULL,
  CONSTRAINT feed_item_url_constraint_check CHECK (url ~* '^(?:http(s)?:\/\/)?[\w.-]+(?:\.[\w\.-]+)+[\w\-\._~:/?#[\]@!\$&''\(\)\*\+,;=.]+$'),
  CONSTRAINT feed_item_feed_type_id_fk FOREIGN KEY (feed_type_id) REFERENCES feed_type (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT feed_item_feed_id_fk FOREIGN KEY (feed_id) REFERENCES feed (id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE UNIQUE INDEX feed_item_id_uindex ON public.feed_item (id);
CREATE UNIQUE INDEX feed_item_url_uindex ON public.feed_item (url);
CREATE UNIQUE INDEX feed_item_checksum_uindex ON public.feed_item (checksum);

CREATE TABLE public.tag
(
  id SERIAL PRIMARY KEY NOT NULL,
  ref_key VARCHAR(255) NOT NULL,
  label VARCHAR(255) NOT NULL,
  date TIMESTAMP DEFAULT NOW() NOT NULL
);
CREATE UNIQUE INDEX tag_id_uindex ON public.tag (id);
CREATE UNIQUE INDEX tag_ref_key_uindex ON public.tag (ref_key);

CREATE TABLE public.feed_item_tag
(
  id SERIAL PRIMARY KEY NOT NULL,
  feed_item_id INT NOT NULL,
  tag_id INT NOT NULL,
  date TIMESTAMP DEFAULT NOW() NOT NULL,
  CONSTRAINT feed_item_tag_feed_item_id_fk FOREIGN KEY (feed_item_id) REFERENCES feed_item (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT feed_item_tag_tag_id_fk FOREIGN KEY (tag_id) REFERENCES tag (id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE UNIQUE INDEX feed_item_tag_id_uindex ON public.feed_item_tag (id);
CREATE UNIQUE INDEX feed_item_tag_feed_item_id_tag_id_uindex ON public.feed_item_tag (feed_item_id, tag_id);

