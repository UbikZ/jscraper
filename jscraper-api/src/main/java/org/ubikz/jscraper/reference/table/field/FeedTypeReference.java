package org.ubikz.jscraper.reference.table.field;

import org.ubikz.jscraper.reference.BaseReference;
import org.ubikz.jscraper.reference.IReference;

public enum FeedTypeReference implements IReference<FeedTypeReference> {
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

    @Override
    public FeedTypeReference getFromValue(String value) {
        return new BaseReference<FeedTypeReference>().getFromValue(values(), value);
    }
}
