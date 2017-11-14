package org.ubikz.jscraper.dictionary.table.field;

import org.ubikz.jscraper.dictionary.BaseDictionary;
import org.ubikz.jscraper.dictionary.IDictionary;

public enum FeedItemTagDictionary implements IDictionary<FeedItemTagDictionary> {
    FEED_ITEM_ID("feed_item_id"),
    TAG_ID("tag_id");

    private String name;

    FeedItemTagDictionary(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public FeedItemTagDictionary getFromValue(String value) {
        return new BaseDictionary<FeedItemTagDictionary>().getFromValue(values(), value);
    }
}
