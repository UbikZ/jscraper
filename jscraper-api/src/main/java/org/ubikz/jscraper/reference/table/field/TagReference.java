package org.ubikz.jscraper.reference.table.field;

import org.ubikz.jscraper.database.reference.IFieldReference;

public enum TagReference implements IFieldReference {
    LABEL("label"),
    URL("url");

    private String name;

    TagReference(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }
}
