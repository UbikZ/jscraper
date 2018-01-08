package org.ubikz.jscraper.api.dal.impl;

import com.rometools.rome.feed.synd.SyndEntry;
import org.springframework.stereotype.Repository;
import org.ubikz.jscraper.api.dal.BaseDal;
import org.ubikz.jscraper.api.dal.model.filter.impl.FeedDalFilter;
import org.ubikz.jscraper.api.dal.model.request.impl.FeedDalRequest;
import org.ubikz.jscraper.database.DatabaseService;
import org.ubikz.jscraper.database.querybuilder.impl.Select;
import org.ubikz.jscraper.database.reference.IFieldReference;
import org.ubikz.jscraper.parser.rss.RssEntry;
import org.ubikz.jscraper.parser.rss.RssParser;
import org.ubikz.jscraper.reference.table.TableReference;
import org.ubikz.jscraper.reference.table.field.FeedReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FeedDal extends BaseDal<FeedDalRequest, FeedDalFilter> {
    public FeedDal(DatabaseService databaseService) {
        super(databaseService);
        this.table = TableReference.FEED;
    }

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

    @Override
    protected Map<IFieldReference, Object> parseRequest(FeedDalRequest request) {
        Map<IFieldReference, Object> values = super.parseRequest(request);

        if (request.getUrl() != null) {
            values.put(FeedReference.URL, request.getUrl());
        }

        if (request.getFeedTypeId() != null) {
            values.put(FeedReference.FEED_TYPE_ID, request.getFeedTypeId());
        }

        return values;
    }

    @Override
    protected void parseFilter(FeedDalFilter filter, Select select, boolean isCount) {
        super.parseFilter(filter, select, isCount);

        if (filter.getUrl() != null) {
            select.where(w -> w.and(p -> p.set(FeedReference.URL, filter.getUrl())));
        }
    }
}
