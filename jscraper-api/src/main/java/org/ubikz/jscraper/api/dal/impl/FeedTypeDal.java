package org.ubikz.jscraper.api.dal.impl;

import org.springframework.stereotype.Repository;
import org.ubikz.jscraper.api.dal.BaseDal;
import org.ubikz.jscraper.api.dal.model.filter.AbstractDalFilter;
import org.ubikz.jscraper.api.dal.model.filter.impl.FeedTypeDalFilter;
import org.ubikz.jscraper.api.dal.model.request.AbstractDalRequest;
import org.ubikz.jscraper.api.dal.model.request.impl.FeedTypeDalRequest;
import org.ubikz.jscraper.database.DatabaseService;
import org.ubikz.jscraper.database.querybuilder.AbstractQuery;

import java.util.Map;

@Repository
public class FeedTypeDal extends BaseDal {
    /**
     * @param databaseService
     */
    public FeedTypeDal(DatabaseService databaseService) {
        super(databaseService);
        this.tableName = "feed_type";
    }

    /**
     * @param request
     * @param created
     * @return
     */
    @Override
    protected Map<String, Object> parseRequest(AbstractDalRequest request, boolean created) {
        FeedTypeDalRequest feedTypeDalRequest = (FeedTypeDalRequest) request;
        Map<String, Object> values = super.parseRequest(feedTypeDalRequest, created);

        if (feedTypeDalRequest.getUrlRegex() != null) {
            values.put("url_regex", feedTypeDalRequest.getUrlRegex());
        }

        if (feedTypeDalRequest.getContentRegex() != null) {
            values.put("content_regex", feedTypeDalRequest.getContentRegex());
        }

        return values;
    }

    /**
     * @param filter
     * @return
     */
    @Override
    protected void parseFilter(AbstractDalFilter filter, AbstractQuery select, boolean isCount) {
        FeedTypeDalFilter feedTypeDalFilter = (FeedTypeDalFilter) filter;
        super.parseFilter(feedTypeDalFilter, select, isCount);

        if (feedTypeDalFilter.getIdList() != null && feedTypeDalFilter.getIdList().size() > 0) {
            select.where("id", "in", feedTypeDalFilter.getIdList());
        }
    }
}