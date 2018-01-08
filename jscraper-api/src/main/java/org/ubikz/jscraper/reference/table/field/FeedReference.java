package org.ubikz.jscraper.reference.table.field;

import org.ubikz.jscraper.database.reference.IFieldReference;

public enum FeedReference implements IFieldReference {
    LABEL("label"),
    URL("url"),
    FEED_TYPE_ID("feed_type_id");

    private String name;

    FeedReference(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }
}
