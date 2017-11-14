package org.ubikz.jscraper.database.querybuilder;

import java.util.Map;

interface IQuery {
    void build();

    String getSQL();

    Map<String, Object> getParameters();
}