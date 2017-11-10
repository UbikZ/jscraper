package org.ubikz.jscraper.api.core.provider.db.qb;

import java.util.Map;

interface IQuery {
    void build();

    String getSQL();

    Map<String, Object> getParameters();
}