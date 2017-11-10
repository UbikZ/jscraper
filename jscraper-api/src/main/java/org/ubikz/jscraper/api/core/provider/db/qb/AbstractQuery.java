package org.ubikz.jscraper.api.core.provider.db.qb;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractQuery implements IQuery {
    protected static final String KEY_FROM = "from";
    protected static final String KEY_WHERE = "where";
    protected static final String KEY_ORWHERE = "orWhere";
    protected static final String KEY_COLUMNS = "columns";

    protected static final String SQL_FROM = "FROM";
    protected static final String SQL_WHERE = "WHERE";
    protected static final String SQL_AND = "AND";
    protected static final String SQL_OR = "OR";

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
        return this.where(where, null);
    }

    public AbstractQuery where(String column, Object value) {
        return this.where(column, "=", value);
    }

    public AbstractQuery where(String column, String op, Object value) {
        return this.where(column, op, value, null);
    }

    public AbstractQuery where(String column, String op, Object value, String cast) {
        return this.where(column, op, value, cast, KEY_WHERE);
    }

    public AbstractQuery where(String column, String op, Object value, String cast, String key) {
        List<String> existingWhere = (List<String>) this.parts.get(key);

        if (value == null) {
            existingWhere.add(column);
        } else {
            String aliasedColumn = this.aliases.getOrDefault(column, column);
            int currIndex = existingWhere.size() + 1;

            String casting = cast == null ? "" : "::" + cast;
            String whereColumn = "w_" + column + "_" + key + "_" + currIndex;
            String whereColumnPlaceholder = ":" + whereColumn;

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
        }

        this.parts.put(key, existingWhere);
        return this;
    }

    public AbstractQuery orWhere(String where) {
        return this.orWhere(where, null);
    }

    public AbstractQuery orWhere(String column, Object value) {
        return this.orWhere(column, "=", value);
    }

    public AbstractQuery orWhere(String column, String op, Object value) {
        return this.orWhere(column, op, value, null);
    }
    
    public AbstractQuery orWhere(String column, String op, Object value, String cast) {
        return this.where(column, op, value, cast, KEY_ORWHERE);
    }

    protected final void handleWhereClauses() {
        List<String> wheres = (List<String>) this.parts.get(KEY_WHERE);
        if (wheres.size() > 0) {
            this.sql.add(SQL_WHERE);
            this.sql.add(wheres.stream().collect(Collectors.joining(" " + SQL_AND + " ")));
        }

        List<String> orWheres = (List<String>) this.parts.get(KEY_ORWHERE);
        if (orWheres.size() > 0) {
            if (!this.sql.contains(SQL_WHERE)) {
                this.sql.add(SQL_WHERE);
            } else {
                this.sql.add(SQL_AND);
            }
            this.sql.add("(" + orWheres.stream().collect(Collectors.joining(" " + SQL_OR + " ")) + ")");
        }
    }

    @Override
    public void build() {
        this.sql = new ArrayList<>();
    }

    @Override
    public final String getSQL() {
        return this.sql.stream().collect(Collectors.joining(" "));
    }

    public final Map<String, Object> getParameters() {
        return this.parameters;
    }

    protected abstract void initParts();
}