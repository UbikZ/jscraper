package org.ubikz.jscraper.database.reference.impl;

public enum OperatorReference {
    EQ("="),
    NEQ("<>"),
    LT("<"),
    LTE("<="),
    GT(">"),
    GTE(">="),
    LIKE("LIKE"),
    NOT("NOT"),
    IN("IN"),
    NOT_IN("NOT IN"),
    AND("AND"),
    OR("OR");

    private String op;

    OperatorReference(String op) {
        this.op = op;
    }

    public String get() {
        return op;
    }
}
