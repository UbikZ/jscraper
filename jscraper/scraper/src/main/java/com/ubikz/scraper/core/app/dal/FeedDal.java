package com.ubikz.scraper.core.app.dal;

import com.ubikz.scraper.core.app.dal.filter.AbstractDalFilter;
import com.ubikz.scraper.core.app.dal.filter.FeedDalFilter;
import com.ubikz.scraper.core.app.dal.request.AbstractDalRequest;
import com.ubikz.scraper.core.app.dal.request.FeedDalRequest;
import com.ubikz.scraper.core.lib.db.DBWrapper;
import com.ubikz.scraper.core.lib.db.qb.AbstractQuery;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class FeedDal extends AbstractDal {
    /**
     * @param dbWrapper
     */
    public FeedDal(DBWrapper dbWrapper) {
        super(dbWrapper);
        this.tableName = "feed";
    }

    /**
     * @param request
     * @param created
     * @return
     */
    @Override
    protected Map<String, Object> parseRequest(AbstractDalRequest request, boolean created) {
        FeedDalRequest feedDalRequest = (FeedDalRequest) request;
        Map<String, Object> values = super.parseRequest(feedDalRequest, created);

        if (feedDalRequest.getUrl() != null) {
            values.put("url", feedDalRequest.getUrl());
        }

        return values;
    }

    /**
     * @param filter
     * @return
     */
    @Override
    protected void parseFilter(AbstractDalFilter filter, AbstractQuery select) {
        FeedDalFilter feedDalFilter = (FeedDalFilter) filter;
        super.parseFilter(feedDalFilter, select);

        if (feedDalFilter.getUrl() != null) {
            select.where("url", feedDalFilter.getUrl());
        }
    }
}