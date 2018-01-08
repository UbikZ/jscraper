package org.ubikz.jscraper.reference.table;

import org.ubikz.jscraper.database.reference.ITableReference;

public enum TableReference implements ITableReference {
    FEED("feed"),
    FEED_PROHIBITED("feed_prohibited"),
    FEED_TYPE("feed_type"),
    FEED_ITEM("feed_item"),
    FEED_ITEM_TAG("feed_item_tag"),
    TAG("tag"),
    TAG_PROHIBITED("tag_prohibited"),
    USER("public.user");

    private String name;

    TableReference(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }
}
