package com.ubikz.scraper.api.dal;

import com.ubikz.scraper.api.dal.filter.FeedDalFilter;
import com.ubikz.scraper.api.dal.request.FeedDalRequest;
import com.ubikz.scraper.lib.db.DBWrapper;
import com.ubikz.scraper.lib.db.qb.AbstractQuery;
import com.ubikz.scraper.lib.db.qb.QueryBuilder;
import com.ubikz.scraper.lib.db.qb.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FeedDal extends AbstractDal {
    public FeedDal(DBWrapper dbWrapper) {
        super(dbWrapper);
    }

    public int createFeed(FeedDalRequest feedDalRequest) {
        QueryBuilder qb = new QueryBuilder();
        Map<String, Object> insertValues = new HashMap<>();

        if (feedDalRequest.getId() != null) {
            insertValues.put("id", feedDalRequest.getId());
        }

        if (feedDalRequest.getUrl() != null) {
            insertValues.put("url", feedDalRequest.getUrl());
        }

        if (feedDalRequest.getLabel() != null) {
            insertValues.put("label", feedDalRequest.getLabel());
        }

        if (feedDalRequest.isEnabled() != null) {
            insertValues.put("enabled", feedDalRequest.isEnabled());
        }

        AbstractQuery insert = qb.insert("feed").values(insertValues);

        return this.request(insert);
    }

    public List<Map<String, Object>> getFeed(FeedDalFilter filter) {
        QueryBuilder qb = new QueryBuilder();
        Select select = qb.select().from("feed");

        if (filter.getId() != null) {
            select.where("id", filter.getId());
        }

        if (filter.getUrl() != null) {
            select.where("url", filter.getUrl());
        }

        if (filter.getLabel() != null) {
            select.where("label", filter.getLabel());
        }

        if (filter.isEnabled() != null) {
            select.where("enabled", filter.isEnabled());
        }

        return this.query(select);
    }
}