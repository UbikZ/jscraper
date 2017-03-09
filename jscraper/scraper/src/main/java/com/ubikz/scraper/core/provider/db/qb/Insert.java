package com.ubikz.scraper.core.provider.db.qb;


import java.util.*;
import java.util.stream.Collectors;

public class Insert extends Edit {
    protected String KEY_VALUES = "values";
    protected String KEY_RETURNING = "returning";
    protected String KEY_ON_CONFLICT = "onconflict";

    private String SQL_INSERT = "INSERT INTO";
    private String SQL_VALUES = "VALUES";
    private String SQL_RETURNING = "RETURNING";
    private String SQL_ON_CONFLICT = "ON CONFLICT";

    public Insert(String table) {
        super(table);
        this.table = table;
    }

    public Insert values(List<Map<String, Object>> listValues) {
        List<List<String>> namedParametersList = new ArrayList<>();

        for (int index = 0; index < listValues.size(); index++) {
            List<String> namedParameters = new ArrayList<>();
            for (Map.Entry<String, Object> value : listValues.get(index).entrySet()) {
                String column = value.getKey();
                String namedParam = column + index;
                this.columns.add(column);
                this.parameters.put(namedParam, value.getValue());
                namedParameters.add(":" + namedParam);
            }
            namedParametersList.add(namedParameters);
        }

        this.parts.put(KEY_VALUES, namedParametersList);

        return this;
    }

    public Insert onConflict(String action) {
        this.parts.put(KEY_ON_CONFLICT, action);
        return this;
    }

    public Insert returning(String column) {
        this.parts.put(KEY_RETURNING, column);
        return this;
    }

    public Insert values(Map<String, Object> values) {
        return this.values(Collections.singletonList(values));
    }

    @Override
    public Insert where(String where) {
        return this;
    }

    @Override
    public Insert where(String column, Object value) {
        return this;
    }

    @Override
    public Insert where(String column, String op, Object value) {
        return this;
    }

    @Override
    public void build() {
        super.build();

        this.sql.add(SQL_INSERT);
        this.sql.add(this.table);
        this.sql.add("(" + this.columns.stream().collect(Collectors.joining(",")) + ")");
        this.sql.add(SQL_VALUES);
        this.sql.add(
                ((List<List<String>>) this.parts.get(KEY_VALUES))
                        .stream()
                        .map(row -> "(" + row.stream().collect(Collectors.joining(",")) + ")")
                        .collect(Collectors.joining(","))
        );

        String onConflict = (String) this.parts.get(KEY_ON_CONFLICT);
        if (onConflict != null) {
            this.sql.add(SQL_ON_CONFLICT);
            this.sql.add(onConflict);
        }

        String returningColumn = (String) this.parts.get(KEY_RETURNING);
        if (returningColumn != null) {
            this.sql.add(SQL_RETURNING);
            this.sql.add(returningColumn);
        }
    }

    @Override
    protected void initParts() {
        super.initParts();
        this.parts.put(KEY_COLUMNS, new HashSet<>());
        this.parts.put(KEY_VALUES, new ArrayList<>());
        this.parts.put(KEY_RETURNING, null);
        this.parts.put(KEY_ON_CONFLICT, null);
    }
}