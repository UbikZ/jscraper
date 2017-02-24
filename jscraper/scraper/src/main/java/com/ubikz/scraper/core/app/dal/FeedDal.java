package com.ubikz.scraper.core.app.dal;

import com.ubikz.scraper.core.app.dal.filter.FeedDalFilter;
import com.ubikz.scraper.core.app.dal.request.FeedDalRequest;
import com.ubikz.scraper.core.lib.db.DBWrapper;
import com.ubikz.scraper.core.lib.db.qb.AbstractQuery;
import com.ubikz.scraper.core.lib.db.qb.QueryBuilder;
import com.ubikz.scraper.core.lib.db.qb.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FeedDal extends AbstractDal {
    /**
     * @param dbWrapper
     */
    public FeedDal(DBWrapper dbWrapper) {
        super(dbWrapper);
    }

    private Map<String, Object> parseRequest(FeedDalRequest request) {
        Map<String, Object> values = new HashMap<>();

        if (request.getUrl() != null) {
            values.put("url", request.getUrl());
        }

        if (request.getLabel() != null) {
            values.put("label", request.getLabel());
        }

        if (request.isEnabled() != null) {
            values.put("enabled", request.isEnabled());
        }

        return values;
    }

    /**
     * @param request
     * @return
     */
    public int createFeed(FeedDalRequest request) {
        QueryBuilder qb = new QueryBuilder();

        AbstractQuery insert = qb.insert("feed").values(this.parseRequest(request)).returning("id");

        return this.insert(insert);
    }

    /**
     * @param request
     * @return
     */
    public int updateFeed(FeedDalRequest request) {
        QueryBuilder qb = new QueryBuilder();
        AbstractQuery update = qb.update("feed").set(this.parseRequest(request)).where("id", request.getId());

        return this.update(update);
    }

    /**
     * @param filter
     * @return
     */
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

        return this.find(select);
    }
}