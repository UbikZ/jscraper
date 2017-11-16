package org.ubikz.jscraper.api.dal.impl;

import com.rometools.rome.feed.synd.SyndEntry;
import org.springframework.stereotype.Repository;
import org.ubikz.jscraper.api.dal.BaseDal;
import org.ubikz.jscraper.api.dal.model.filter.AbstractDalFilter;
import org.ubikz.jscraper.api.dal.model.filter.impl.FeedDalFilter;
import org.ubikz.jscraper.api.dal.model.request.AbstractDalRequest;
import org.ubikz.jscraper.api.dal.model.request.impl.FeedDalRequest;
import org.ubikz.jscraper.database.DatabaseService;
import org.ubikz.jscraper.database.querybuilder.AbstractQuery;
import org.ubikz.jscraper.parser.rss.RssEntry;
import org.ubikz.jscraper.parser.rss.RssParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FeedDal extends BaseDal {
    /**
     * @param databaseService
     */
    public FeedDal(DatabaseService databaseService) {
        super(databaseService);
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