package org.ubikz.jscraper.database.querybuilder.impl;


import org.ubikz.jscraper.database.querybuilder.Query;
import org.ubikz.jscraper.database.querybuilder.parts.impl.*;
import org.ubikz.jscraper.database.reference.IFieldReference;
import org.ubikz.jscraper.database.reference.ITableReference;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Insert extends Query<Insert> {
    public Insert() {
        super();
        this.allowedParts.addAll(Arrays.asList(
                TablePart.class,
                IntoValuePart.class,
                OnPart.class,
                OnDoPart.class,
                ReturningPart.class
        ));
    }

    public Insert table(Consumer<TablePart> consumer) {
        return consumePart(consumer, TablePart.class);
    }

    public Insert table(ITableReference table) {
        return table(t -> t.set(table));
    }

    public Insert values(Consumer<IntoValuePart> consumer) {
        return consumePart(consumer, IntoValuePart.class);
    }

    public Insert values(List<Map<IFieldReference, Object>> values) {
        return values(v -> v.setValues(values));
    }

    public Insert values(Map<IFieldReference, Object> values) {
        return values(v -> v.addValues(values));
    }

    public Insert on(Consumer<OnPart> consumer) {
        return consumePart(consumer, OnPart.class);
    }

    public Insert onConflict() {
        return on(o -> o.conflict());
    }

    public Insert onDo(Consumer<OnDoPart> consumer) {
        return consumePart(consumer, OnDoPart.class);
    }

    public Insert onDoNothing() {
        return onDo(OnDoPart::nothing);
    }

    public Insert returning(Consumer<ReturningPart> consumer) {
        return consumePart(consumer, ReturningPart.class);
    }

    public Insert returning(IFieldReference column) {
        return returning(r -> r.setColumn(column));
    }
}
