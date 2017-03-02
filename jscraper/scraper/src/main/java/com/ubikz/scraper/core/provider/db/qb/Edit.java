package com.ubikz.scraper.core.provider.db.qb;


import java.util.ArrayList;

abstract class Edit extends AbstractQuery {
    public Edit(String table) {
        super();
        this.table = table;
    }

    @Override
    protected void initParts() {
        this.parts.put(KEY_WHERE, new ArrayList<>());
    }
}