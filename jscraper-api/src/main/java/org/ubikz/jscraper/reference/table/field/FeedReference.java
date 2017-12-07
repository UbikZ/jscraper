package org.ubikz.jscraper.reference.table.field;

import org.ubikz.jscraper.reference.BaseReference;
import org.ubikz.jscraper.reference.IReference;

public enum FeedReference implements IReference<FeedReference> {
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

    @Override
    public FeedReference getFromValue(String value) {
        return new BaseReference<FeedReference>().getFromValue(values(), value);
    }
}
