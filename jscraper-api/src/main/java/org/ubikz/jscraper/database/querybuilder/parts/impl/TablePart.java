package org.ubikz.jscraper.database.querybuilder.parts.impl;

import com.google.common.base.Strings;
import org.ubikz.jscraper.database.querybuilder.parts.Part;
import org.ubikz.jscraper.database.reference.ITableReference;
import org.ubikz.jscraper.database.reference.impl.CommonReference;
import org.ubikz.jscraper.exception.ApplicativeException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TablePart extends Part {
    protected Map<ITableReference, String> tables;

    public TablePart() {
        this.tables = new HashMap<>();
    }

    public void set(ITableReference table) {
        set(table, null);
    }

    public void set(ITableReference table, String alias) {
        this.tables.put(table, alias);
    }

    public void set(Map<ITableReference, String> tables) {
        this.tables = tables;
    }

    private String format(ITableReference table, String alias) {
        return Strings.isNullOrEmpty(alias)
                ? table.get()
                : String.format("%s %s %s", table.get(), CommonReference.AS.get(), alias);
    }

    @Override
    public String toSQL() {
        super.toSQL();

        List<String> tables = this.tables.entrySet().stream().map(e -> format(e.getKey(), e.getValue())).collect(Collectors.toList());

        return tables.stream().collect(Collectors.joining(", "));
    }

    @Override
    public void checkIntegrity() {
        if (tables.isEmpty()) {
            throw new ApplicativeException("Tables should not be null.");
        }
    }
}
