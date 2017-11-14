package org.ubikz.jscraper.dictionary.table;

import org.ubikz.jscraper.dictionary.BaseDictionary;
import org.ubikz.jscraper.dictionary.IDictionary;

public enum TableDictionary implements IDictionary<TableDictionary> {
    FEED("feed"),
    FEED_PROHIBITED("feed_prohibited"),
    FEED_TYPE("feed_type"),
    FEED_ITEM("feed_item"),
    FEED_ITEM_TAG("feed_item_tag"),
    TAG("tag"),
    TAG_PROHIBITED("tag_prohibited"),
    USER("user");

    private String name;

    TableDictionary(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public TableDictionary getFromValue(String value) {
        return new BaseDictionary<TableDictionary>().getFromValue(values(), value);
    }
}
