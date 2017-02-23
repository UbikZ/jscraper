package com.ubikz.scraper.core.lib.db.qb;

import java.util.Map;

interface IQuery {
    void build();

    String getSQL();

    Map<String, Object> getParameters();
}