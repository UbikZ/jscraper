package com.ubikz.scraper.lib.db.qb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class DBSelect {
    // Key use within maps
    private final String KEY_DISTINCT = "distinct";
    private final String KEY_COLUMNS = "columns";
    private final String KEY_FROM = "from";
    private final String KEY_WHERE = "where";
    private final String KEY_ORDER = "order";
    private final String KEY_JOIN = "inner_join";

    // SQL elements
    private final String SQL_WILDCARD = "*";
    private final String SQL_SELECT = "SELECT";
    private final String SQL_FROM = "FROM";
    private final String SQL_WHERE = "WHERE";
    private final String SQL_DISTINCT = "DISTINCT";
    private final String SQL_ORDER_BY = "ORDER BY";
    private final String SQL_AND = "AND";
    private final String SQL_AS = "AS";
    private final String SQL_OR = "OR";
    private final String SQL_ON = "ON";
    private final String SQL_ASC = "ASC";
    private final String SQL_DESC = "DESC";

    // Parts to use to build request
    private Map<String, Object> parts = new HashMap<>();


    private String table;
    private String[] columns;
    private List<Map<String, String>> joins;

    public DBSelect(String... columns) {
        this.initParts();
        this.parseColumns(columns);
    }

    public DBSelect from(String table) {
        this.parts.put(KEY_FROM, table);
        return this;
    }

    public DBSelect join(String table, String on) {
        @SuppressWarnings("unchecked")
        List<String> existingJoins = (List<String>) this.parts.get(KEY_JOIN);
        existingJoins.add("JOIN " + table + " " + SQL_ON + " " + on);
        this.parts.put(KEY_JOIN, existingJoins);
        return this;
    }

    public DBSelect where(String where) {
        return this.where(where, null, null);
    }

    public DBSelect where(String column, Object value) {
        return this.where(column, "=", value);
    }

    public DBSelect where(String column, String op, Object value) {
        List<String> existingWhere = (List<String>) this.parts.get(KEY_WHERE);
        existingWhere.add(column + op + value);
        this.parts.put(KEY_WHERE, existingWhere);
        return this;
    }

    public String toString() {
        return this.toSQL();
    }

    public String toSQL() {
        List<String> sql = new ArrayList<>();
        sql.add(SQL_SELECT);

        boolean distinct = (boolean) this.parts.get(KEY_DISTINCT);
        if (distinct) {
            sql.add(SQL_DISTINCT);
        }

        List<String> columns = (List<String>) this.parts.get(KEY_COLUMNS);
        if (columns.size() > 0) {
            sql.add(columns.stream().collect(Collectors.joining(", ")));
        } else {
            sql.add(SQL_WILDCARD);
        }

        sql.add(SQL_FROM);
        sql.add((String) this.parts.get(KEY_FROM));

        List<String> joins = (List<String>) this.parts.get(KEY_JOIN);
        if (joins.size() > 0) {
            sql.add(joins.stream().collect(Collectors.joining(" ")));
        }

        List<String> wheres = (List<String>) this.parts.get(KEY_WHERE);
        if (wheres.size() > 0) {
            sql.add(SQL_WHERE);
            sql.add(wheres.stream().collect(Collectors.joining(" " + SQL_AND + " ")));
        }

        return sql.stream().collect(Collectors.joining(" "));
    }

    private void initParts() {
        this.parts.put(KEY_DISTINCT, false);
        this.parts.put(KEY_COLUMNS, new ArrayList<>());
        this.parts.put(KEY_FROM, null);
        this.parts.put(KEY_JOIN, new ArrayList<>());
        this.parts.put(KEY_WHERE, new ArrayList<>());
        this.parts.put(KEY_ORDER, new ArrayList<>());
    }

    private void parseColumns(String... columns) {
        List<String> existingColumns = (List<String>) this.parts.get(KEY_COLUMNS);
        for (String column : columns) {
            existingColumns.add(column);
        }
        this.parts.put(KEY_COLUMNS, existingColumns);
    }
}