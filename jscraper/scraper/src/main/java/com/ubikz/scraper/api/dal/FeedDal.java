package com.ubikz.scraper.api.dal;

import com.ubikz.scraper.lib.db.DBWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FeedDal {
    private DBWrapper dbWrapper;

    @Autowired
    public FeedDal(DBWrapper dbWrapper) {
        this.dbWrapper = dbWrapper;
    }

    public int createFeed() {
        String sql = "INSERT INTO feed (ref_key, label, url) VALUES (?, ?, ?) RETURNING id";
        return dbWrapper.jdbcTemplate.queryForObject(
                sql,
                new Object[]{"pouet", "test", "http://test.com"},
                Integer.class
        );
    }
}