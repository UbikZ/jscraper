package com.ubikz.scraper.api.dal;

import com.ubikz.scraper.lib.db.DBWrapper;
import org.springframework.beans.factory.annotation.Autowired;

abstract class AbstractDal {
    protected DBWrapper dbWrapper;

    @Autowired
    public AbstractDal(DBWrapper dbWrapper) {
        this.dbWrapper = dbWrapper;
    }
}