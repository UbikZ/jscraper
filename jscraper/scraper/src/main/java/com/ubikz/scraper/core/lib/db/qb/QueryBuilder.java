package com.ubikz.scraper.core.lib.db.qb;

public class QueryBuilder {
    public QueryBuilder() {
    }

    public Select select(String... columns) {
        return new Select(columns);
    }

    public Insert insert(String table) {
        return new Insert(table);
    }

    public Update update(String table) {
        return new Update(table);
    }
}