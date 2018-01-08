package org.ubikz.jscraper.reference.table.field;

import org.ubikz.jscraper.database.reference.IFieldReference;

public enum TagProhibitedReference implements IFieldReference {
    LABEL("label");

    private String name;

    TagProhibitedReference(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }
}
