package com.ubikz.scraper.lib.db.qb;

public class DBQueryBuilder {
    public DBQueryBuilder() {
    }

    public DBSelect select(String... columns) {
        return new DBSelect(columns);
    }
}