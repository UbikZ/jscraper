package org.ubikz.jscraper.database.querybuilder.parts;

import java.util.List;

public interface IPart {
    String toSQL();

    List<Parameter> getParameters();

    void checkIntegrity();
}
