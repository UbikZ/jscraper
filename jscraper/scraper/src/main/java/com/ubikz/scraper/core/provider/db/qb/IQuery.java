package com.ubikz.scraper.core.provider.db.qb;

import java.util.Map;

interface IQuery {
    void build();

    String getSQL();

    Map<String, Object> getParameters();
}