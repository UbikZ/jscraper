package org.ubikz.jscraper.reference.table.field;

import org.ubikz.jscraper.reference.BaseReference;
import org.ubikz.jscraper.reference.IReference;

public enum FeedProhibitedReference implements IReference<FeedProhibitedReference> {
    LABEL("label");

    private String name;

    FeedProhibitedReference(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }

    @Override
    public FeedProhibitedReference getFromValue(String value) {
        return new BaseReference<FeedProhibitedReference>().getFromValue(values(), value);
    }
}
