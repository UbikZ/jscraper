package org.ubikz.jscraper.reference.table.field;

import org.ubikz.jscraper.reference.BaseReference;
import org.ubikz.jscraper.reference.IReference;

public enum CommonReference implements IReference<CommonReference> {
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

    @Override
    public CommonReference getFromValue(String value) {
        return new BaseReference<CommonReference>().getFromValue(values(), value);
    }
}