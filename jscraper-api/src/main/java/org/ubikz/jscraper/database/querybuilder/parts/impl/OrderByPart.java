package org.ubikz.jscraper.database.querybuilder.parts.impl;

import org.assertj.core.util.Strings;
import org.ubikz.jscraper.database.querybuilder.parts.Part;
import org.ubikz.jscraper.database.reference.IFieldReference;
import org.ubikz.jscraper.database.reference.impl.CommonReference;
import org.ubikz.jscraper.database.reference.impl.DirectionReference;
import org.ubikz.jscraper.exception.ApplicativeException;

public class OrderByPart extends Part {
    private String column;
    private DirectionReference direction;

    public void set(IFieldReference column) {
        set(column.get(), DirectionReference.ASC);
    }

    public void set(String column) {
        set(column, DirectionReference.ASC);
    }

    public void set(IFieldReference column, DirectionReference direction) {
        set(column.get(), direction);
    }

    public void set(String column, DirectionReference direction) {
        this.column = column;
        this.direction = direction;
    }

    @Override
    public String toSQL() {
        super.toSQL();

        return String.format("%s %s %s", CommonReference.ORDER_BY, column, direction.get());
    }

    @Override
    public void checkIntegrity() {
        if (Strings.isNullOrEmpty(column)) {
            throw new ApplicativeException("Column should not be empty.");
        }
    }
}
