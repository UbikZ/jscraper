package org.ubikz.jscraper.reference.table.field;

import org.ubikz.jscraper.reference.BaseReference;
import org.ubikz.jscraper.reference.IReference;

public enum FeedItemTagReference implements IReference<FeedItemTagReference> {
    FEED_ITEM_ID("feed_item_id"),
    TAG_ID("tag_id");

    private String name;

    FeedItemTagReference(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }

    @Override
    public FeedItemTagReference getFromValue(String value) {
        return new BaseReference<FeedItemTagReference>().getFromValue(values(), value);
    }
}
