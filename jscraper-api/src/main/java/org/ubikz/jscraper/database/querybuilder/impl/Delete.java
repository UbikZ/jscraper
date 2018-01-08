package org.ubikz.jscraper.database.querybuilder.impl;

import org.ubikz.jscraper.database.querybuilder.Query;
import org.ubikz.jscraper.database.querybuilder.parts.impl.TablePart;
import org.ubikz.jscraper.database.querybuilder.parts.impl.WhereChainPart;
import org.ubikz.jscraper.database.reference.ITableReference;

import java.util.Arrays;
import java.util.function.Consumer;

public class Delete extends Query<Delete> {
    public Delete() {
        super();
        this.allowedParts.addAll(Arrays.asList(
                TablePart.class,
                WhereChainPart.class
        ));
    }

    public Delete from(Consumer<TablePart> consumer) {
        return consumePart(consumer, TablePart.class);
    }

    public Delete from(ITableReference table) {
        return from(t -> t.set(table));
    }

    public Delete where(Consumer<WhereChainPart> consumer) {
        return consumePart(consumer, WhereChainPart.class);
    }
}
