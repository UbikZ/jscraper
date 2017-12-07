package org.ubikz.jscraper.reference.table.field;

import org.ubikz.jscraper.reference.BaseReference;
import org.ubikz.jscraper.reference.IReference;

public enum TagReference implements IReference<TagReference> {
    LABEL("label"),
    URL("url");

    private String name;

    TagReference(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }

    @Override
    public TagReference getFromValue(String value) {
        return new BaseReference<TagReference>().getFromValue(values(), value);
    }
}
