package org.ubikz.jscraper.api.core.provider.db.qb;


import java.util.ArrayList;

public abstract class Edit extends AbstractQuery {
    public Edit(String table) {
        super();
        this.table = table;
    }

    @Override
    protected void initParts() {
        this.parts.put(KEY_WHERE, new ArrayList<>());
        this.parts.put(KEY_ORWHERE, new ArrayList<>());
    }
}