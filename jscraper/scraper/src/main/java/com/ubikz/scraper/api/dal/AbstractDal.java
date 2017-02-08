package com.ubikz.scraper.api.dal;

import com.ubikz.scraper.lib.db.DBConnector;

abstract class AbstractDal {
    private DBConnector dbConnector = null;

    public AbstractDal() {
        this.dbConnector = new DBConnector();
    }

    public DBConnector getDbConnector() {
        return dbConnector;
    }

    public void setDbConnector(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }
}