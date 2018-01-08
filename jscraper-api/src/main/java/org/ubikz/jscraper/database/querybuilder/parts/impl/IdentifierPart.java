package org.ubikz.jscraper.database.querybuilder.parts.impl;

import org.ubikz.jscraper.database.querybuilder.parts.Part;
import org.ubikz.jscraper.database.reference.impl.CommonReference;
import org.ubikz.jscraper.database.reference.impl.IdentifierReference;
import org.ubikz.jscraper.exception.ApplicativeException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IdentifierPart extends Part {
    private IdentifierReference identifier;
    private boolean distinct = false;

    public void set(IdentifierReference identifier) {
        this.identifier = identifier;
    }

    public void distinct(boolean distinct) {
        this.distinct = distinct;
    }

    public void distinct() {
        distinct(true);
    }

    @Override
    public String toSQL() {
        super.toSQL();

        List<String> result = new ArrayList<>();

        result.add(identifier.get());

        if (distinct) {
            result.add(CommonReference.DISTINCT.get());
        }

        return result.stream().collect(Collectors.joining(" "));
    }

    @Override
    public void checkIntegrity() {
        if (identifier == null) {
            throw new ApplicativeException("Identifier should not be null.");
        }
    }
}
