package org.ubikz.jscraper.reference.table;

import org.ubikz.jscraper.reference.BaseReference;
import org.ubikz.jscraper.reference.IReference;

public enum TableReference implements IReference<TableReference> {
    FEED("feed"),
    FEED_PROHIBITED("feed_prohibited"),
    FEED_TYPE("feed_type"),
    FEED_ITEM("feed_item"),
    FEED_ITEM_TAG("feed_item_tag"),
    TAG("tag"),
    TAG_PROHIBITED("tag_prohibited"),
    USER("user");

    private String name;

    TableReference(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }

    @Override
    public TableReference getFromValue(String value) {
        return new BaseReference<TableReference>().getFromValue(values(), value);
    }
}
