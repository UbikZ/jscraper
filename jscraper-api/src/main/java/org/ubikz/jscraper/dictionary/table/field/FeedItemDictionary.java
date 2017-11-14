package org.ubikz.jscraper.dictionary.table.field;

import org.ubikz.jscraper.dictionary.BaseDictionary;
import org.ubikz.jscraper.dictionary.IDictionary;

public enum FeedItemDictionary implements IDictionary<FeedItemDictionary> {
    LABEL("label"),
    URL("url"),
    COMMENT("comment"),
    CHECKSUM("checksum"),
    VIEWED("viewed"),
    APPROVED("approved"),
    REPOSTED("reposted"),
    SENT("sent"),
    FEED_ID("feed_id");

    private String name;

    FeedItemDictionary(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public FeedItemDictionary getFromValue(String value) {
        return new BaseDictionary<FeedItemDictionary>().getFromValue(values(), value);
    }
}
