package org.ubikz.jscraper.database.reference.impl;

public enum DirectionReference {
    ASC("ASC"),
    DESC("DESC");

    private String direction;

    DirectionReference(String direction) {
        this.direction = direction;
    }

    public String get() {
        return direction;
    }
}
