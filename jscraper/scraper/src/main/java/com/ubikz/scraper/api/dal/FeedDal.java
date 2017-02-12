package com.ubikz.scraper.api.dal;

import com.ubikz.scraper.api.dal.filter.FeedDalFilter;
import com.ubikz.scraper.lib.db.DBWrapper;
import com.ubikz.scraper.lib.db.qb.DBQueryBuilder;
import com.ubikz.scraper.lib.db.qb.DBSelect;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class FeedDal extends AbstractDal {
    public FeedDal(DBWrapper dbWrapper) {
        super(dbWrapper);
    }

    public int createFeed() {
        String sql = "INSERT INTO feed (ref_key, label, url) VALUES (?, ?, ?) RETURNING id";
        return this.dbWrapper.jdbcTemplate.queryForObject(
                sql,
                new Object[]{"pouet", "test", "http://test.com"},
                Integer.class
        );
    }

    public List<Map<String, Object>> getFeed(FeedDalFilter filter) {
        DBQueryBuilder qb = new DBQueryBuilder();
        DBSelect select = qb.select().from("feed");

        if (filter.getId() != null) {
            select.where("id", filter.getId());
        }

        if (filter.getUrl() != null) {
            select.where("url", filter.getUrl());
        }

        if (filter.getLabel() != null) {
            select.where("label", filter.getLabel());
        }

        if (filter.getReferenceKey() != null) {
            select.where("ref_key", filter.getReferenceKey());
        }

        if (filter.isEnabled() != null) {
            select.where("enabled", filter.isEnabled());
        }

        System.out.println(" >>>>>>> " + select.toSQL());

        return this.dbWrapper.jdbcTemplate.queryForList(select.toSQL());
    }
}