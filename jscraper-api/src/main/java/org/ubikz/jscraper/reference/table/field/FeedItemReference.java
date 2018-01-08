package org.ubikz.jscraper.reference.table.field;

import org.ubikz.jscraper.database.reference.IFieldReference;

public enum FeedItemReference implements IFieldReference {
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
}
