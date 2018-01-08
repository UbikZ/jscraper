package org.ubikz.jscraper.database.querybuilder.parts.impl;

import org.ubikz.jscraper.database.reference.impl.CommonReference;

public class FromPart extends TablePart {
    @Override
    public String toSQL() {
        return String.format("%s %s", CommonReference.FROM, super.toSQL());
    }
}
