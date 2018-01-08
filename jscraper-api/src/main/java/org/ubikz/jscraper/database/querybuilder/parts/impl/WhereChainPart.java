package org.ubikz.jscraper.database.querybuilder.parts.impl;

import org.ubikz.jscraper.database.querybuilder.parts.Parameter;
import org.ubikz.jscraper.database.querybuilder.parts.Part;
import org.ubikz.jscraper.database.reference.impl.CommonReference;
import org.ubikz.jscraper.database.reference.impl.OperatorReference;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class WhereChainPart extends Part {
    private List<WherePart> and;
    private List<WherePart> or;

    public WhereChainPart() {
        this.and = new ArrayList<>();
        this.or = new ArrayList<>();
    }

    public WhereChainPart and(Consumer<WherePart> consumer) {
        return add(and, consumer);
    }

    public WhereChainPart or(Consumer<WherePart> consumer) {
        return add(or, consumer);
    }

    private WhereChainPart add(List<WherePart> list, Consumer<WherePart> consumer) {
        WherePart wherePart = new WherePart();
        consumer.accept(wherePart);
        list.add(wherePart);

        return this;
    }

    private String formatAnd() {
        return format(and, OperatorReference.AND);
    }

    private String formatOr() {
        return format(or, OperatorReference.OR);
    }

    private String format(List<WherePart> whereParts, OperatorReference op) {
        return whereParts.stream().map(WherePart::toSQL).collect(Collectors.joining(op.get()));
    }

    @Override
    public String toSQL() {
        super.toSQL();
        List<String> sql = new ArrayList<>();

        if (!and.isEmpty() || !or.isEmpty()) {
            sql.add(CommonReference.WHERE.get());
            if (!and.isEmpty()) {
                sql.add(formatAnd());
            }
            if (!or.isEmpty()) {
                if (!and.isEmpty()) {
                    sql.add(OperatorReference.AND.get());
                }
                sql.add(String.format("(%s)", formatOr()));
            }
        }

        return sql.stream().collect(Collectors.joining(" "));
    }

    @Override
    public List<Parameter> getParameters() {
        return super.getParameters();
    }

    @Override
    public void checkIntegrity() {
    }
}
