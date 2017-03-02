package com.ubikz.scraper.core.app.dal;

import com.ubikz.scraper.core.app.dal.request.AbstractDalRequest;
import com.ubikz.scraper.core.app.dal.request.FeedTypeDalRequest;
import com.ubikz.scraper.core.provider.db.DBWrapper;
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
}