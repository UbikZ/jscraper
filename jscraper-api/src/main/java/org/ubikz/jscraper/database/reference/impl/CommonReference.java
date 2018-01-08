package org.ubikz.jscraper.database.reference.impl;

public enum CommonReference {
    FROM("FROM"),
    COUNT("COUNT"),
    AS("AS"),
    DISTINCT("DISTINCT"),
    TOP("TOP"),
    ALL("*"),
    SET("SET"),
    VALUES("VALUES"),
    ON("ON"),
    ON_CONFLICT("ON CONFLICT"),
    ON_CONSTRAINT("ON CONSTRAINT"),
    DO("DO"),
    NOTHING("NOTHING"),
    WHERE("WHERE"),
    HAVING("HAVING"),
    ORDER_BY("ORDER BY"),
    GROUP_BY("GROUP BY"),
    LIMIT("LIMIT"),
    OFFSET("OFFSET"),
    RETURNING("RETURNING");

    private String common;

    CommonReference(String common) {
        this.common = common;
    }

    public String get() {
        return common;
    }
}
