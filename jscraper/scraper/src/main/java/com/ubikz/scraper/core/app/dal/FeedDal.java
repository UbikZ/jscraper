package com.ubikz.scraper.core.app.dal;

import com.rometools.rome.feed.synd.SyndEntry;
import com.ubikz.scraper.core.app.dal.filter.AbstractDalFilter;
import com.ubikz.scraper.core.app.dal.filter.FeedDalFilter;
import com.ubikz.scraper.core.app.dal.request.AbstractDalRequest;
import com.ubikz.scraper.core.app.dal.request.FeedDalRequest;
import com.ubikz.scraper.core.provider.db.DBWrapper;
import com.ubikz.scraper.core.provider.db.qb.AbstractQuery;
import com.ubikz.scraper.core.provider.rss.RssEntry;
import com.ubikz.scraper.core.provider.rss.RssParser;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
        this.tableName = "feed";
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getRssFeedList(FeedDalFilter filter) throws Exception {
        List<Map<String, Object>> resultList = new ArrayList<>();

        for (RssEntry rssEntry : (new RssParser(filter.getUrl(), filter.getProhibitedFeedList())).getEntryList()) {
            Map<String, Object> article = new HashMap<>();
            SyndEntry entry = rssEntry.getEntry();

            // We build base object article
            article.put("url", entry.getLink());
            article.put("label", entry.getTitle());
            article.put("date", entry.getPublishedDate());
            article.put("author", entry.getAuthor());
            article.put("tags", rssEntry.buildTagList(filter.getProhibitedTagList()));
            article.put("pictures", rssEntry.getPictureLinks(filter.getUrlRegex()));

            resultList.add(article);
        }

        return resultList;
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

        if (feedDalRequest.getFeedTypeId() != null) {
            values.put("feed_type_id", feedDalRequest.getFeedTypeId());
        }

        return values;
    }

    /**
     * @param filter
     * @return
     */
    @Override
    protected void parseFilter(AbstractDalFilter filter, AbstractQuery select, boolean isCount) {
        FeedDalFilter feedDalFilter = (FeedDalFilter) filter;
        super.parseFilter(feedDalFilter, select, isCount);

        if (feedDalFilter.getUrl() != null) {
            select.where("url", feedDalFilter.getUrl());
        }
    }
}