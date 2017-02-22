package com.ubikz.scraper.lib.db.qb;


import java.util.ArrayList;

abstract class Edit extends AbstractQuery {
    protected String KEY_VALUES = "values";

    public Edit(String table) {
        super();
        this.table = table;
    }

    @Override
    protected void initParts() {
        this.parts.put(KEY_WHERE, new ArrayList<>());
    }
}