package com.ubikz.scraper.core.lib.db.qb;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class Delete extends AbstractQuery {
    // SQL elements
    private final String SQL_DELETE = "DELETE";

    public Delete() {
        super();
    }

    public Delete from(String table) {
        this.parts.put(KEY_FROM, table);
        return this;
    }

    @Override
    public Delete where(String where) {
        return (Delete) super.where(where);
    }

    @Override
    public Delete where(String column, Object value) {
        return (Delete) super.where(column, value);
    }

    @Override
    public Delete where(String column, String op, Object value) {
        return (Delete) super.where(column, op, value);
    }

    @Override
    public void build() {
        super.build();

        this.sql.add(SQL_DELETE);
        this.sql.add(SQL_FROM);
        this.sql.add((String) this.parts.get(KEY_FROM));

        List<String> wheres = (List<String>) this.parts.get(KEY_WHERE);
        if (wheres.size() > 0) {
            this.sql.add(SQL_WHERE);
            this.sql.add(wheres.stream().collect(Collectors.joining(" " + SQL_AND + " ")));
        }
    }

    @Override
    protected void initParts() {
        this.parts.put(KEY_FROM, null);
        this.parts.put(KEY_WHERE, new ArrayList<>());
    }

    @Override
    public String toString() {
        return this.getSQL();
    }
}