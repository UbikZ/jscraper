package org.ubikz.jscraper.reference.table.field;

import org.ubikz.jscraper.database.reference.IFieldReference;

public enum FeedTypeReference implements IFieldReference {
    LABEL("label"),
    URL_REGEX("url_regex"),
    CONTENT_REGEX("content_regex");

    private String name;

    FeedTypeReference(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }
}
