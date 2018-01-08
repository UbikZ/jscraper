package org.ubikz.jscraper.database.querybuilder.parts.impl;

import org.assertj.core.util.Strings;
import org.ubikz.jscraper.database.querybuilder.parts.Parameter;
import org.ubikz.jscraper.database.querybuilder.parts.Part;
import org.ubikz.jscraper.database.reference.IFieldReference;
import org.ubikz.jscraper.database.reference.impl.OperatorReference;
import org.ubikz.jscraper.exception.ApplicativeException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WherePart extends Part {
    private String column;
    private OperatorReference operator;
    private List<Parameter> value;
    private String cast;

    public void set(String column) {
        this.column = column;
    }

    public void set(IFieldReference column, Object value) {
        set(column.get(), value);
    }

    @SuppressWarnings("unchecked")
    public void set(String column, Object objValue) {
        set(column);
        this.value = (objValue instanceof List)
                ? ((List<Object>) objValue).stream().map(v -> new Parameter(column, v)).collect(Collectors.toList())
                : Collections.singletonList(new Parameter(column, objValue));
    }

    public void set(IFieldReference column, OperatorReference op, Object value) {
        set(column.get(), op, value);
    }

    public void set(String column, OperatorReference op, Object value) {
        set(column, value);
        this.operator = op;
    }

    public void set(IFieldReference column, OperatorReference op, Object value, String cast) {
        set(column.get(), op, value, cast);
    }

    public void set(String column, OperatorReference op, Object value, String cast) {
        set(column, op, value);
        this.cast = cast;
    }

    @Override
    public String toSQL() {
        super.toSQL();

        String castType = Strings.isNullOrEmpty(cast) ? "" : String.format("::%s", cast);
        String formatedValue = (value.size() > 1 && OperatorReference.IN.equals(operator))
                ? "(" + value.stream().map(Parameter::getName).collect(Collectors.joining(", ")) + ")"
                : value.get(0).getPName();

        return String.format("%s %s %s%s", column, operator.get(), formatedValue, castType);
    }

    @Override
    public List<Parameter> getParameters() {
        return value;
    }

    @Override
    public void checkIntegrity() {
        if (Strings.isNullOrEmpty(column)) {
            throw new ApplicativeException("Column should not be empty.");
        }

        if (value == null || value.isEmpty()) {
            throw new ApplicativeException("Value should not be empty.");
        }
    }
}
