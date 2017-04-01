package com.ubikz.scraper.core.provider.db.qb;

import java.util.*;
import java.util.stream.Collectors;

abstract public class AbstractQuery implements IQuery {
    protected final String KEY_FROM = "from";
    protected final String KEY_WHERE = "where";
    protected final String KEY_COLUMNS = "columns";

    protected final String SQL_FROM = "FROM";
    protected final String SQL_WHERE = "WHERE";
    protected final String SQL_AND = "AND";
    protected final String SQL_OR = "OR";

    protected String table;
    protected List<String> sql;
    protected Set<String> columns = new HashSet<>();
    protected Map<String, Object> parts = new HashMap<>();
    protected Map<String, Object> parameters = new HashMap<>();
    private Map<String, String> aliases = new HashMap<>();

    public AbstractQuery() {
        this.initParts();
    }

    public AbstractQuery aliases(Map<String, String> aliases) {
        this.aliases = aliases;
        return this;
    }

    public AbstractQuery where(String where) {
        return this.where(where, null, null, null);
    }

    public AbstractQuery where(String column, Object value) {
        return this.where(column, "=", value, null);
    }

    public AbstractQuery where(String column, String op, Object value) {
        return this.where(column, "=", value, null);
    }

    public AbstractQuery where(String column, String op, Object value, String cast) {
        String casting = cast == null ? "" : "::" + cast;
        String aliasedColumn = this.aliases.getOrDefault(column, column);
        String whereColumn = "w_" + column;
        String whereColumnPlaceholder = ":" + whereColumn;

        List<String> existingWhere = (List<String>) this.parts.get(KEY_WHERE);

        if (op.toLowerCase().equals("in")) {
            if (value instanceof List) {
                List<String> inConditions = new ArrayList<>();
                int index = 0;
                for (Object element : (List<Object>) value) {
                    inConditions.add(whereColumnPlaceholder + index);
                    this.parameters.put(whereColumn + index, element);
                    index++;
                }
                whereColumnPlaceholder = "(" + inConditions.stream().collect(Collectors.joining(",")) + ")";
            }
        } else {
            this.parameters.put(whereColumn, value);
        }

        existingWhere.add(aliasedColumn + " " + op + " " + whereColumnPlaceholder + casting);
        this.parts.put(KEY_WHERE, existingWhere);
        return this;
    }

    @Override
    public void build() {
        this.sql = new ArrayList<>();
    }

    @Override
    final public String getSQL() {
        return this.sql.stream().collect(Collectors.joining(" "));
    }

    final public Map<String, Object> getParameters() {
        return this.parameters;
    }

    abstract protected void initParts();
}