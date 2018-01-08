package org.ubikz.jscraper.database.querybuilder.impl;

import org.ubikz.jscraper.database.querybuilder.Query;
import org.ubikz.jscraper.database.querybuilder.parts.impl.*;
import org.ubikz.jscraper.database.reference.IFieldReference;
import org.ubikz.jscraper.database.reference.ITableReference;
import org.ubikz.jscraper.database.reference.impl.JoinReference;

import java.util.Arrays;
import java.util.function.Consumer;

public class Select extends Query<Select> {

    public Select() {
        super();
        this.parts.add(0, new WhereChainPart());
        this.allowedParts.addAll(Arrays.asList(
                TablePart.class,
                ColumnPart.class,
                JoinPart.class,
                GroupByPart.class,
                OrderByPart.class,
                WhereChainPart.class,
                PaginatePart.class
        ));
    }

    public Select from(Consumer<TablePart> consumer) {
        return consumePart(consumer, TablePart.class);
    }

    public Select from(ITableReference table, String alias) {
        return from(t -> t.set(table, alias));
    }

    public Select from(ITableReference table) {
        return from(table, null);
    }

    public Select column(Consumer<ColumnPart> consumer) {
        return consumePart(consumer, ColumnPart.class);
    }

    public Select column(String column) {
        return column(c -> c.addColumn(column));
    }

    public Select join(Consumer<JoinPart> consumer) {
        return consumePart(consumer, JoinPart.class);
    }

    public Select joinLeft(Consumer<JoinPart> consumer) {
        return consumePart(consumer, new JoinPart().setIdentifier(JoinReference.LEFT));
    }

    public Select groupBy(Consumer<GroupByPart> consumer) {
        return consumePart(consumer, GroupByPart.class);
    }

    public Select groupBy(String... columns) {
        return groupBy(g -> g.setColumns(columns));
    }

    public Select orderBy(Consumer<OrderByPart> consumer) {
        return consumePart(consumer, OrderByPart.class);
    }

    public Select orderBy(String column) {
        return orderBy(o -> o.set(column));
    }

    public Select orderBy(IFieldReference column) {
        return orderBy(o -> o.set(column));
    }

    public Select where(Consumer<WhereChainPart> consumer) {
        consumer.accept((WhereChainPart) parts.get(0));

        return this;
    }

    public Select paginate(Consumer<PaginatePart> consumer) {
        return consumePart(consumer, PaginatePart.class);
    }

    public Select limit(Integer limit) {
        return paginate(p -> p.setLimit(limit));
    }

    public Select offset(Integer offset) {
        return paginate(p -> p.setOffset(offset));
    }
}
