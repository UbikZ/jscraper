package org.ubikz.jscraper.database.querybuilder.parts.impl;

import org.ubikz.jscraper.database.querybuilder.parts.Parameter;
import org.ubikz.jscraper.database.querybuilder.parts.Part;
import org.ubikz.jscraper.database.reference.IFieldReference;
import org.ubikz.jscraper.database.reference.impl.CommonReference;
import org.ubikz.jscraper.exception.ApplicativeException;

import java.util.*;
import java.util.stream.Collectors;

public class IntoValuePart extends Part {
    private List<Map<IFieldReference, Parameter>> values;

    public IntoValuePart() {
        values = new ArrayList<>();
    }

    public void setValues(List<Map<IFieldReference, Object>> values) {
        values.forEach(this::addValues);
    }

    public void addValues(Map<IFieldReference, Object> objValues) {
        Map<IFieldReference, Parameter> result = new HashMap<>();
        objValues.forEach((key, value) -> result.put(key, new Parameter(key, value)));
        values.add(result);
    }

    private Set<IFieldReference> getColumns() {
        return values
                .stream()
                .map(m -> m.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    private String formatColumns() {
        return String.format("(%s)", getColumns().stream().map(IFieldReference::get).collect(Collectors.joining(", ")));
    }

    private String formatValues() {
        List<String> strings = values
                .stream()
                .map(m -> m.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList()))
                .map(v -> "(" + v.stream().map(Parameter::getPName).collect(Collectors.joining(", ")) + ")")
                .collect(Collectors.toList());

        return strings.isEmpty()
                ? ""
                : strings.stream().collect(Collectors.joining(", "));
    }

    @Override
    public String toSQL() {
        super.toSQL();

        return String.format("%s %s %s", formatColumns(), CommonReference.VALUES, formatValues());
    }

    @Override
    public List<Parameter> getParameters() {
        return values
                .stream()
                .map(m -> m.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public void checkIntegrity() {
        if (values == null) {
            throw new ApplicativeException("Values should not be null.");
        }
    }
}
