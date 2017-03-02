package com.ubikz.scraper.core.provider.db.qb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class Select extends AbstractQuery {
    protected final String SQL_AS = "AS";
    // Key use within maps
    private final String KEY_DISTINCT = "distinct";
    private final String KEY_ORDER = "order";
    private final String KEY_GROUP = "group";
    private final String KEY_JOIN = "inner_join";
    // SQL elements
    private final String SQL_WILDCARD = "*";
    private final String SQL_SELECT = "SELECT";
    private final String SQL_FROM = "FROM";
    private final String SQL_DISTINCT = "DISTINCT";
    private final String SQL_ORDER_BY = "ORDER BY";
    private final String SQL_GROUP_BY = "GROUP BY";
    private final String SQL_ON = "ON";
    private final String SQL_ASC = "ASC";
    private final String SQL_DESC = "DESC";

    private List<Map<String, String>> joins;

    public Select(String... columns) {
        super();
        this.parseColumns(columns);
    }

    /**
     * @param table
     * @return
     */
    public Select from(String table) {
        this.from(table, null);
        return this;
    }

    /**
     * @param table
     * @param alias
     * @return
     */
    public Select from(String table, String alias) {
        this.parts.put(KEY_FROM, this.buildAlias(table, alias));
        return this;
    }

    /**
     * @param table
     * @param on
     * @return
     */
    public Select join(String table, String on) {
        this.join(table, null, on);
        return this;
    }

    /**
     * @param table
     * @param alias
     * @param on
     * @return
     */
    public Select join(String table, String alias, String on) {
        @SuppressWarnings("unchecked")
        List<String> existingJoins = (List<String>) this.parts.get(KEY_JOIN);
        existingJoins.add("JOIN " + this.buildAlias(table, alias) + " " + SQL_ON + " " + on);
        this.parts.put(KEY_JOIN, existingJoins);
        return this;
    }

    /**
     * @param table
     * @param alias
     * @return
     */
    private String buildAlias(String table, String alias) {
        List<String> aliasedTable = new ArrayList<>();
        aliasedTable.add(table);

        if (alias != null) {
            aliasedTable.add(SQL_AS);
            aliasedTable.add(alias);
        }

        return aliasedTable.stream().collect(Collectors.joining(" "));
    }

    /**
     * @param columns
     */
    private void parseColumns(String... columns) {
        this.parts.put(KEY_COLUMNS, Arrays.asList(columns));
    }

    /**
     * @param columns
     * @return
     */
    public Select groupBy(String... columns) {
        this.parts.put(KEY_GROUP, Arrays.asList(columns));
        return this;
    }

    @Override
    public Select where(String where) {
        return (Select) super.where(where);
    }

    @Override
    public Select where(String column, Object value) {
        return (Select) super.where(column, value);
    }

    @Override
    public Select where(String column, String op, Object value) {
        return (Select) super.where(column, op, value);
    }

    @Override
    public void build() {
        super.build();

        this.sql.add(SQL_SELECT);

        boolean distinct = (boolean) this.parts.get(KEY_DISTINCT);
        if (distinct) {
            this.sql.add(SQL_DISTINCT);
        }

        List<String> columns = (List<String>) this.parts.get(KEY_COLUMNS);
        if (columns.size() > 0) {
            this.sql.add(columns.stream().collect(Collectors.joining(", ")));
        } else {
            this.sql.add(SQL_WILDCARD);
        }

        this.sql.add(SQL_FROM);
        this.sql.add((String) this.parts.get(KEY_FROM));

        List<String> joins = (List<String>) this.parts.get(KEY_JOIN);
        if (joins.size() > 0) {
            this.sql.add(joins.stream().collect(Collectors.joining(" ")));
        }

        List<String> wheres = (List<String>) this.parts.get(KEY_WHERE);
        if (wheres.size() > 0) {
            this.sql.add(SQL_WHERE);
            this.sql.add(wheres.stream().collect(Collectors.joining(" " + SQL_AND + " ")));
        }

        List<String> groupBy = (List<String>) this.parts.get(KEY_GROUP);
        if (groupBy.size() > 0) {
            this.sql.add(SQL_GROUP_BY);
            this.sql.add("(" + groupBy.stream().collect(Collectors.joining(",")) + ")");
        }
    }

    @Override
    protected void initParts() {
        this.parts.put(KEY_DISTINCT, false);
        this.parts.put(KEY_COLUMNS, new ArrayList<>());
        this.parts.put(KEY_FROM, null);
        this.parts.put(KEY_JOIN, new ArrayList<>());
        this.parts.put(KEY_WHERE, new ArrayList<>());
        this.parts.put(KEY_ORDER, new ArrayList<>());
        this.parts.put(KEY_GROUP, new ArrayList<>());
    }

    @Override
    public String toString() {
        return this.getSQL();
    }
}