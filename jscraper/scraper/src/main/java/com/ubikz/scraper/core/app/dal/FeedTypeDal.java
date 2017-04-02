package com.ubikz.scraper.core.app.dal;

import com.ubikz.scraper.core.app.dal.filter.AbstractDalFilter;
import com.ubikz.scraper.core.app.dal.filter.FeedTypeDalFilter;
import com.ubikz.scraper.core.app.dal.request.AbstractDalRequest;
import com.ubikz.scraper.core.app.dal.request.FeedTypeDalRequest;
import com.ubikz.scraper.core.provider.db.DBWrapper;
import com.ubikz.scraper.core.provider.db.qb.AbstractQuery;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class FeedTypeDal extends AbstractDal {
    /**
     * @param dbWrapper
     */
    public FeedTypeDal(DBWrapper dbWrapper) {
        super(dbWrapper);
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
    protected void parseFilter(AbstractDalFilter filter, AbstractQuery select) {
        FeedTypeDalFilter feedTypeDalFilter = (FeedTypeDalFilter) filter;
        super.parseFilter(feedTypeDalFilter, select);

        if (feedTypeDalFilter.getIdList() != null && feedTypeDalFilter.getIdList().size() > 0) {
            select.where("id", "in", feedTypeDalFilter.getIdList());
        }
    }
}