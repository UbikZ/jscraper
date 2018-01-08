package org.ubikz.jscraper.reference.table.field;

import org.ubikz.jscraper.database.reference.IFieldReference;

public enum FeedItemTagReference implements IFieldReference {
    FEED_ITEM_ID("feed_item_id"),
    TAG_ID("tag_id");

    private String name;

    FeedItemTagReference(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }
}
