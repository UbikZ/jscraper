INSERT INTO public.tag_prohibited (label) VALUES
  ('i'), ('me'), ('you'), ('your'), ('she'), ('he'), ('it'), ('they'), ('the'), ('a'), ('an'), ('its'), ('her'),
  ('him'), ('their'), ('this'), ('that'), ('thats'), ('these'), ('those'), ('at'), ('from'), ('to'), ('of'), ('for'),
  ('in'), ('out'), ('all'), ('and'), ('up'), ('down'), ('do'), ('done'), ('who'), ('which'), ('when'), ('any'),
  ('would'), ('should'), ('is'), ('has'), ('had'), ('been'), ('be'), ('are'), ('them'), ('have'), ('uh'), ('oh'),
  ('go'), ('going'), ('find'), ('get'), ('getting'), ('than'), ('with'), ('my'), ('think'), ('give'), ('giving'),
  ('meet'), ('build'), ('built'), ('can'), ('never'), ('enough'), ('will'), ('take'), ('taking'), ('every'), ('by'),
  ('over'), ('more');

INSERT INTO public.feed_prohibited (label) VALUES
  ('(m)'), ('[m]'), ('*m*');

INSERT INTO public.feed_type (id, label, url_regex) VALUES
  (1, 'reddit', '^https://reddit.com/');

INSERT INTO public.feed (label, url, feed_type_id) VALUES
  ('NSFW', 'https://www.reddit.com/r/nsfw/new/.rss', 1),
  ('RealGirls', 'https://www.reddit.com/r/RealGirls/new/.rss', 1);
-- ('NSFW_GIF',	'https://www.reddit.com/r/NSFW_GIF/new/.rss', 1),
-- ('ASS',	'https://www.reddit.com/r/ass/new/.rss', 1),
-- ('Boobies',	'https://www.reddit.com/r/Boobies/new/.rss', 1),
-- ('BustyPetite',	'https://www.reddit.com/r/BustyPetite/new/.rss', 1),
-- ('nsfw_gifs',	'https://www.reddit.com/r/nsfw_gifs/new/.rss', 1),
-- ('Amateur',	'https://www.reddit.com/r/Amateur/new/.rss', 1),
-- ('NSFW411',	'https://www.reddit.com/r/NSFW411/new/.rss', 1),
-- ('CollegeAmateurs',	'https://www.reddit.com/r/CollegeAmateurs/new/.rss', 1),
-- ('ChristianGirls',	'https://www.reddit.com/r/ChristianGirls/new/.rss', 1),
-- ('gonewildplus',	'https://www.reddit.com/r/gonewildplus/new/.rss', 1),
-- ('xsmallgirls',	'https://www.reddit.com/r/xsmallgirls/new/.rss', 1),
-- ('NSFWFunny',	'https://www.reddit.com/r/NSFWFunny/new/.rss', 1),
-- ('lesbians',	'https://www.reddit.com/r/lesbians/new/.rss', 1),
-- ('creampies',	'https://www.reddit.com/r/creampies/new/.rss', 1),
-- ('dykesgonewild',	'https://www.reddit.com/r/dykesgonewild/new/.rss', 1),
-- ('snapleaks',	'https://www.reddit.com/r/snapleaks/new/.rss', 1),
-- ('embarrasedgirls',	'https://www.reddit.com/r/HappyEmbarrassedGirls/new/.rss', 1),
-- ('barelylegalteens',	'https://www.reddit.com/r/barelylegalteens/new/.rss', 1),
-- ('dirtysmall',	'https://www.reddit.com/r/dirtysmall/new/.rss', 1),
-- ('cumslut',	'https://www.reddit.com/r/cumsluts/new/.rss', 1),
-- ('gonewildtube',	'https://www.reddit.com/r/GoneWildTube/new/.rss', 1),
-- ('gonewildcouples',	'https://www.reddit.com/r/GWCouples/new/.rss', 1),
-- ('gonewildnerdy',	'https://www.reddit.com/r/GWNerdy/new/.rss', 1),
-- ('gonewildwork',	'https://www.reddit.com/r/workgonewild/new/.rss', 1),
-- ('sexinfrontofothers',	'https://www.reddit.com/r/SexInFrontOfOthers/new/.rss', 1),
-- ('unshamed',	'https://www.reddit.com/r/Unashamed/new/.rss', 1),
-- ('drunkgirls',	'https://www.reddit.com/r/DrunkGirls/new/.rss', 1),
-- ('highreznsfw',	'https://www.reddit.com/r/HighResNSFW/new/.rss', 1),
-- ('iwtfh',	'https://www.reddit.com/r/iWantToFuckHer/new/.rss', 1),
-- ('nsfwhardcore',	'https://www.reddit.com/r/nsfwhardcore/new/.rss', 1),
-- ('porn',	'https://www.reddit.com/r/porn/new/.rss', 1),
-- ('porn60fps',	'https://www.reddit.com/r/60fpsporn/new/.rss', 1),
-- ('festivalsluts',	'https://www.reddit.com/r/FestivalSluts/new/.rss', 1),
-- ('camwhores',	'https://www.reddit.com/r/camwhores/new/.rss', 1),
-- ('sluttystrangers',	'https://www.reddit.com/r/SluttyStrangers/new/.rss', 1),
-- ('holdtheman',	'https://www.reddit.com/r/holdthemoan/new/.rss', 1),
-- ('anal',	'https://www.reddit.com/r/anal/new/.rss', 1),
-- ('wifesharing',	'https://www.reddit.com/r/wifesharing/new/.rss', 1),
-- ('jilling',	'https://www.reddit.com/r/jilling/new/.rss', 1),
-- ('blowjobs',	'https://www.reddit.com/r/Blowjobs/new/.rss', 1),
-- ('springbreakers',	'https://www.reddit.com/r/springbreakers/new/.rss', 1),
