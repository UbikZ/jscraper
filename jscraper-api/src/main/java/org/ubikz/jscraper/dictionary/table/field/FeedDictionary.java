package org.ubikz.jscraper.dictionary.table.field;

import org.ubikz.jscraper.dictionary.BaseDictionary;
import org.ubikz.jscraper.dictionary.IDictionary;

public enum FeedDictionary implements IDictionary<FeedDictionary> {
    LABEL("label"),
    URL("url"),
    FEED_TYPE_ID("feed_type_id");

    private String name;

    FeedDictionary(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public FeedDictionary getFromValue(String value) {
        return new BaseDictionary<FeedDictionary>().getFromValue(values(), value);
    }
}
