package org.ubikz.jscraper.database.querybuilder.parts;

import org.ubikz.jscraper.database.reference.IFieldReference;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

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
        this.name = String.format("%s_%s", column, encode(value));
    }

    public static String encode(Object value) {
        return Base64.getEncoder().encodeToString(value.toString().getBytes(StandardCharsets.UTF_8));
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
