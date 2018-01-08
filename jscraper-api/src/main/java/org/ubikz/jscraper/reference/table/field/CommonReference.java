package org.ubikz.jscraper.reference.table.field;

import org.ubikz.jscraper.database.reference.IFieldReference;

public enum CommonReference implements IFieldReference {
    ID("id"),
    DATE("date"),
    ENABLED("enabled");

    private String name;

    CommonReference(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }
}
