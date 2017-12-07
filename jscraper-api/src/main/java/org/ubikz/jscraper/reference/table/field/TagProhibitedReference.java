package org.ubikz.jscraper.reference.table.field;

import org.ubikz.jscraper.reference.BaseReference;
import org.ubikz.jscraper.reference.IReference;

public enum TagProhibitedReference implements IReference<TagProhibitedReference> {
    LABEL("label");

    private String name;

    TagProhibitedReference(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }

    @Override
    public TagProhibitedReference getFromValue(String value) {
        return new BaseReference<TagProhibitedReference>().getFromValue(values(), value);
    }
}
