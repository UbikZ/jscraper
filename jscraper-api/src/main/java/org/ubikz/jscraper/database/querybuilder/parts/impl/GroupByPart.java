package org.ubikz.jscraper.database.querybuilder.parts.impl;

import org.ubikz.jscraper.database.reference.impl.CommonReference;

public class GroupByPart extends ColumnPart {
    @Override
    public String toSQL() {
        super.toSQL();

        return String.format("%s %s", CommonReference.GROUP_BY, super.toSQL());
    }
}
