package com.ubikz.scraper.core.provider.rss;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class RssParser {
    private List<RssEntry> entryList;

    public RssParser(String url) throws Exception {
        SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
        this.entryList = feed.getEntries().stream().map(RssEntry::new).collect(Collectors.toList());
    }

    public List<RssEntry> getEntryList() {
        return entryList;
    }
}