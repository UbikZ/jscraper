package com.ubikz.scraper.core.provider.parser.rss;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.net.URL;

public class RssParser {
    private URL url;
    private SyndFeed feed;

    public RssParser(String url) throws Exception {
        this.url = new URL(url);
        this.feed = new SyndFeedInput().build(new XmlReader(this.url));
    }
}