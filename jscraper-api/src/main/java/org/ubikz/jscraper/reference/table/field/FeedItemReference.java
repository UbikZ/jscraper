package org.ubikz.jscraper.reference.table.field;

import org.ubikz.jscraper.reference.BaseReference;
import org.ubikz.jscraper.reference.IReference;

public enum FeedItemReference implements IReference<FeedItemReference> {
    LABEL("label"),
    URL("url"),
    COMMENT("comment"),
    CHECKSUM("checksum"),
    VIEWED("viewed"),
    APPROVED("approved"),
    REPOSTED("reposted"),
    SENT("sent"),
    TAGS("tags"),
    FEED_ID("feed_id");

    private String name;

    FeedItemReference(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }

    @Override
    public FeedItemReference getFromValue(String value) {
        return new BaseReference<FeedItemReference>().getFromValue(values(), value);
    }
}
