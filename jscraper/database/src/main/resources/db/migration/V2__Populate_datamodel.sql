INSERT INTO public.tag_prohibited (label) VALUES
  ('i'), ('me'), ('you'), ('your'), ('she'), ('he'), ('it'), ('they'), ('the'), ('a'), ('an'), ('its'), ('her'),
  ('him'), ('their'), ('this'), ('that'), ('thats'), ('these'), ('those'), ('at'), ('from'), ('to'), ('of'), ('for'),
  ('in'), ('out'), ('all'), ('and'), ('up'), ('down'), ('do'), ('done'), ('who'), ('which'), ('when'), ('any'),
  ('would'), ('should'), ('is'), ('has'), ('had'), ('been'), ('be'), ('are'), ('them'), ('have'), ('uh'), ('oh'),
  ('go'), ('going'), ('find'), ('get'), ('getting'), ('than'), ('with'), ('my'), ('think'), ('give'), ('giving'),
  ('meet'), ('build'), ('built'), ('can'), ('never'), ('enough'), ('will'), ('take'), ('taking'), ('every'), ('by'),
  ('over'), ('more'), ('what'), ('off'), ('on'), ('well'), ('not'), ('or'), ('there'), ('if'), ('anything'), ('anyway'),
  ('we');

INSERT INTO public.feed_prohibited (label) VALUES
  ('(m)'), ('[m]'), ('(M)'), ('[M]'), ('*m*'), ('[Request]');

INSERT INTO public.feed_type (id, label, url_regex) VALUES
  (1, 'reddit', '^https?://(www\.)?reddit\.com(.*)');