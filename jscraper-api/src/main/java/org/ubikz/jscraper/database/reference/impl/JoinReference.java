package org.ubikz.jscraper.database.reference.impl;

public enum JoinReference {
    INNER("INNER JOIN"),
    LEFT("LEFT JOIN"),
    RIGHT("RIGHT JOIN");

    private String join;

    JoinReference(String join) {
        this.join = join;
    }

    public String get() {
        return join;
    }
}
