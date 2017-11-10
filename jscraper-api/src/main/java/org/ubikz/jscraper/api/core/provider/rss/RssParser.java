package org.ubikz.jscraper.api.core.provider.rss;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class RssParser {
    private List<RssEntry> entryList;

    public RssParser(String url, List<String> prohibitedFeeds) throws Exception {
        SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
        this.entryList = feed.getEntries()
                .stream()
                .filter(entry -> this.checkEntry(entry, prohibitedFeeds))
                .map(RssEntry::new)
                .collect(Collectors.toList());
    }

    public List<RssEntry> getEntryList() {
        return entryList;
    }

    /**
     * @param entry
     * @param prohibitedFeeds
     * @return
     */
    private boolean checkEntry(SyndEntry entry, List<String> prohibitedFeeds) {
        boolean valid = true;
        for (String prohibitedFeed : prohibitedFeeds) {
            if (entry.getTitle().contains(prohibitedFeed)) {
                valid = false;
                break;
            }
        }

        return valid;
    }
}