package org.ubikz.jscraper.database.querybuilder.parts;

import org.ubikz.jscraper.database.reference.IFieldReference;

public class Parameter {
    private String column;
    private String name;
    private Object value;

    public Parameter(IFieldReference column, Object value) {
        this(column.get(), value);
    }

    public Parameter(String column, Object value) {
        this.column = column;
        this.value = value;
        this.name = String.format("%s_%s", column, value.toString());
    }

    public String getName() {
        return name;
    }

    public String getPName() {
        return String.format(":%s", getName());
    }

    public Object getValue() {
        return value;
    }
}
