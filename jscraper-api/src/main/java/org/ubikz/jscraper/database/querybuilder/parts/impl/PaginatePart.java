package org.ubikz.jscraper.database.querybuilder.parts.impl;

import org.ubikz.jscraper.database.querybuilder.parts.Part;
import org.ubikz.jscraper.database.reference.impl.CommonReference;

public class PaginatePart extends Part {
    private Integer limit;
    private Integer offset;

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    private String format(CommonReference ref, Integer integer) {
        return integer == null
                ? ""
                : String.format("%s %d", ref.get(), integer);
    }

    @Override
    public String toSQL() {
        super.toSQL();

        return format(CommonReference.LIMIT, limit) + " " + format(CommonReference.OFFSET, offset);
    }

    @Override
    public void checkIntegrity() {
    }
}
