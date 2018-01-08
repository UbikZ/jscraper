package org.ubikz.jscraper.database.querybuilder.parts.impl;

import com.google.common.base.Strings;
import org.ubikz.jscraper.database.querybuilder.parts.Part;
import org.ubikz.jscraper.database.reference.IFieldReference;
import org.ubikz.jscraper.database.reference.impl.CommonReference;
import org.ubikz.jscraper.exception.ApplicativeException;

public class ReturningPart extends Part {
    private String column;

    public void setColumn(IFieldReference column) {
        setColumn(column.get());
    }

    public void setColumn(String column) {
        this.column = column;
    }

    @Override
    public String toSQL() {
        super.toSQL();

        return String.format("%s %s", CommonReference.RETURNING.get(), column);
    }

    @Override
    public void checkIntegrity() {
        if (Strings.isNullOrEmpty(column)) {
            throw new ApplicativeException("Column should not be empty.");
        }
    }
}
