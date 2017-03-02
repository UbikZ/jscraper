package com.ubikz.scraper.core.app.dal;

import com.ubikz.scraper.core.provider.db.DBWrapper;
import org.springframework.stereotype.Repository;

@Repository
public class TagDal extends AbstractDal {
    /**
     * @param dbWrapper
     */
    public TagDal(DBWrapper dbWrapper) {
        super(dbWrapper);
        this.tableName = "tag";
    }
}