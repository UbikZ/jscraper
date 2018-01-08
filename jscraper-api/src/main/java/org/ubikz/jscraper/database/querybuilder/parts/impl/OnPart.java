package org.ubikz.jscraper.database.querybuilder.parts.impl;

import org.ubikz.jscraper.database.querybuilder.parts.Part;
import org.ubikz.jscraper.database.reference.impl.CommonReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OnPart extends Part {
    private CommonReference key;
    private List<String> elements;

    public OnPart() {
        this.elements = new ArrayList<>();
    }

    public void conflict(String... conflicts) {
        key = CommonReference.ON_CONFLICT;
        elements = Arrays.asList(conflicts);
    }

    public void constraint(String... constraints) {
        if (constraints.length > 0) {
            key = CommonReference.ON_CONSTRAINT;
            elements = Arrays.asList(constraints);
        }
    }

    @Override
    public String toSQL() {
        super.toSQL();

        return String.format("%s %s", key.get(), buildListToString(elements, false));
    }

    @Override
    public void checkIntegrity() {
    }
}
