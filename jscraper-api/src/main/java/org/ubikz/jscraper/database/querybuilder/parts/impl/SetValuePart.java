package org.ubikz.jscraper.database.querybuilder.parts.impl;

import org.ubikz.jscraper.database.querybuilder.parts.Parameter;
import org.ubikz.jscraper.database.querybuilder.parts.Part;
import org.ubikz.jscraper.database.reference.IFieldReference;
import org.ubikz.jscraper.exception.ApplicativeException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SetValuePart extends Part {
    private Map<IFieldReference, Parameter> values;

    public SetValuePart() {
        values = new LinkedHashMap<>();
    }

    public void setValues(Map<IFieldReference, Object> values) {
        values.forEach(this::addValue);
    }

    public void addValue(IFieldReference column, Object value) {
        values.put(column, new Parameter(column, value));
    }

    @Override
    public String toSQL() {
        super.toSQL();

        return values
                .entrySet()
                .stream()
                .map(e -> String.format("%s=:%s", e.getKey().get(), e.getValue().getName()))
                .collect(Collectors.joining(", "));
    }

    @Override
    public List<Parameter> getParameters() {
        return values.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public void checkIntegrity() {
        if (values == null) {
            throw new ApplicativeException("Values should not be null.");
        }
    }
}
