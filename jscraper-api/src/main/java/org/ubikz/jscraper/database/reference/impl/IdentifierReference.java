package org.ubikz.jscraper.database.reference.impl;

public enum IdentifierReference {
    SELECT("SELECT"),
    INSERT("INSERT INTO"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    private String identifier;

    IdentifierReference(String identifier) {
        this.identifier = identifier;
    }

    public String get() {
        return identifier;
    }
}
