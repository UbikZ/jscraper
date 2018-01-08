package org.ubikz.jscraper.database.querybuilder.parts.impl;

import com.google.common.base.Strings;
import org.ubikz.jscraper.database.querybuilder.parts.Part;
import org.ubikz.jscraper.database.reference.IFieldReference;
import org.ubikz.jscraper.database.reference.impl.CommonReference;
import org.ubikz.jscraper.exception.ApplicativeException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ColumnPart extends Part {
    private Map<String, String> columns;

    public ColumnPart() {
        columns = new HashMap<>();
    }

    public void setColumns(String... columnRefs) {
        Arrays.stream(columnRefs).forEach(c -> columns.put(c, null));
    }

    public void setColumns(IFieldReference... columnRefs) {
        Arrays.stream(columnRefs).forEach(c -> columns.put(c.get(), null));
    }

    public void setColumns(Map<IFieldReference, String> columnRefs) {
        columns = new HashMap<>();
        columnRefs.forEach((column, alias) -> columns.put(column.get(), alias));
    }

    public void addColumn(IFieldReference column) {
        addColumn(column.get());
    }

    public void addColumn(String column) {
        addColumn(column, null);
    }

    public void addColumn(IFieldReference column, String alias) {
        addColumn(column.get(), alias);
    }

    public void addColumn(String column, String alias) {
        columns.put(column, alias);
    }

    private String format(String column, String alias) {
        return Strings.isNullOrEmpty(alias)
                ? column
                : String.format("%s %s %s", column, CommonReference.AS.get(), alias);
    }

    @Override
    public String toSQL() {
        super.toSQL();

        List<String> tables = columns.entrySet().stream().map(e -> format(e.getKey(), e.getValue())).collect(Collectors.toList());

        return tables.stream().collect(Collectors.joining(", "));
    }

    @Override
    public void checkIntegrity() {
        if (columns == null) {
            throw new ApplicativeException("Columns should not be null.");
        }
    }
}
