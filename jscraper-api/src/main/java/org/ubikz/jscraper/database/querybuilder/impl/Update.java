package org.ubikz.jscraper.database.querybuilder.impl;


import org.ubikz.jscraper.database.querybuilder.Query;
import org.ubikz.jscraper.database.querybuilder.parts.impl.SetValuePart;
import org.ubikz.jscraper.database.querybuilder.parts.impl.TablePart;
import org.ubikz.jscraper.database.querybuilder.parts.impl.WhereChainPart;
import org.ubikz.jscraper.database.reference.IFieldReference;
import org.ubikz.jscraper.database.reference.ITableReference;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Consumer;

public class Update extends Query<Update> {
    public Update() {
        super();
        this.allowedParts.addAll(Arrays.asList(
                TablePart.class,
                SetValuePart.class,
                WhereChainPart.class
        ));
    }

    public Update table(Consumer<TablePart> consumer) {
        return consumePart(consumer, TablePart.class);
    }

    public Update table(ITableReference table) {
        return table(t -> t.set(table));
    }

    public Update values(Consumer<SetValuePart> consumer) {
        return consumePart(consumer, SetValuePart.class);
    }

    public Update values(Map<IFieldReference, Object> values) {
        return values(v -> v.setValues(values));
    }

    public Update where(Consumer<WhereChainPart> consumer) {
        return consumePart(consumer, WhereChainPart.class);
    }
}
