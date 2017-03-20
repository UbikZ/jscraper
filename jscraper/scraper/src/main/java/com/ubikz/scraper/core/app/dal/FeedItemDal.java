package com.ubikz.scraper.core.app.dal;

import com.ubikz.scraper.core.app.dal.filter.AbstractDalFilter;
import com.ubikz.scraper.core.app.dal.filter.FeedItemDalFilter;
import com.ubikz.scraper.core.app.dal.request.AbstractDalRequest;
import com.ubikz.scraper.core.app.dal.request.FeedItemDalRequest;
import com.ubikz.scraper.core.app.dal.request.FeedItemTagDalRequest;
import com.ubikz.scraper.core.provider.db.DBWrapper;
import com.ubikz.scraper.core.provider.db.qb.AbstractQuery;
import com.ubikz.scraper.core.provider.db.qb.QueryBuilder;
import com.ubikz.scraper.core.provider.db.qb.Select;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FeedItemDal extends AbstractDal {
    /**
     * @param dbWrapper
     */
    public FeedItemDal(DBWrapper dbWrapper) {
        super(dbWrapper);
        this.tableName = "feed_item";
    }

    @Override
    protected Select getBaseSelect(AbstractDalFilter filter) {
        QueryBuilder qb = new QueryBuilder();
        AbstractQuery select = qb
                .select("fi.*")
                .from(this.tableName, "fi")
                .groupBy("fi.id")
                .aliases(new HashMap<String, String>() {{
                    put("id", "fi.id");
                }});

        this.parseFilter(filter, select);
        return (Select) select;
    }

    /**
     * @param request
     * @return
     */
    public int createTag(FeedItemTagDalRequest request) {
        return this.createTags(Collections.singletonList(request));
    }

    /**
     * @param requestList
     * @return
     */
    public int createTags(List<FeedItemTagDalRequest> requestList) {
        QueryBuilder qb = new QueryBuilder();

        AbstractQuery insert = qb
                .insert("feed_item_tag")
                .values(this.parseFeedItemTagRequest(requestList))
                .onConflict("DO NOTHING");

        return this.update(insert);
    }

    /**
     * @param requestList
     * @return
     */
    private List<Map<String, Object>> parseFeedItemTagRequest(List<FeedItemTagDalRequest> requestList) {
        List<Map<String, Object>> valuesList = new ArrayList<>();

        for (FeedItemTagDalRequest request : requestList) {
            Map<String, Object> values = new HashMap<>();

            if (request.getFeedItemId() != null) {
                values.put("feed_item_id", request.getFeedItemId());
            }

            if (request.getTagId() != null) {
                values.put("tag_id", request.getTagId());
            }

            valuesList.add(values);
        }

        return valuesList;
    }

    /**
     * @param request
     * @param created
     * @return
     */
    @Override
    protected Map<String, Object> parseRequest(AbstractDalRequest request, boolean created) {
        FeedItemDalRequest feedItemDalRequest = (FeedItemDalRequest) request;
        Map<String, Object> values = super.parseRequest(feedItemDalRequest, created);

        if (feedItemDalRequest.getFeedId() != null) {
            values.put("feed_id", feedItemDalRequest.getFeedId());
        }

        if (feedItemDalRequest.getUrl() != null) {
            values.put("url", feedItemDalRequest.getUrl());
        }

        if (feedItemDalRequest.getChecksum() != null) {
            values.put("checksum", feedItemDalRequest.getChecksum());
        }

        if (feedItemDalRequest.getViewed() != null) {
            values.put("viewed", feedItemDalRequest.getViewed());
        }

        if (feedItemDalRequest.getApproved() != null) {
            values.put("approved", feedItemDalRequest.getApproved());
        }

        if (feedItemDalRequest.getReposted() != null) {
            values.put("reposted", feedItemDalRequest.getReposted());
        }

        if (feedItemDalRequest.getSent() != null) {
            values.put("sent", feedItemDalRequest.getSent());
        }

        return values;
    }

    /**
     * @param filter
     * @return
     */
    @Override
    protected void parseFilter(AbstractDalFilter filter, AbstractQuery aSelect) {
        Select select = (Select) aSelect;
        FeedItemDalFilter feedItemDalFilter = (FeedItemDalFilter) filter;
        super.parseFilter(feedItemDalFilter, select);

        if (feedItemDalFilter.getTagNames() != null && feedItemDalFilter.getTagNames().size() > 0) {
            select.addColumn("string_agg(DISTINCT fit.tag_id::character varying, ',') AS tags")
                    .join("feed_item_tag", "fit", "fi.id = fit.feed_item_id")
                    .join("tag", "t", "fit.tag_id = t.id")
                    .where("t.label", "in", feedItemDalFilter.getTagNames());
        } else {
            select.addColumn("string_agg(DISTINCT fit.tag_id::character varying, ',') AS tags")
                    .joinLeft("feed_item_tag", "fit", "fi.id = fit.feed_item_id");
        }

        if (feedItemDalFilter.getUrl() != null) {
            select.where("url", feedItemDalFilter.getUrl());
        }

        if (feedItemDalFilter.getFeedId() != null) {
            select.where("feed_id", feedItemDalFilter.getUrl());
        }

        if (feedItemDalFilter.getUrl() != null) {
            select.where("url", feedItemDalFilter.getUrl());
        }

        if (feedItemDalFilter.getChecksum() != null) {
            select.where("checksum", feedItemDalFilter.getChecksum());
        }

        if (feedItemDalFilter.getViewed() != null) {
            select.where("viewed", feedItemDalFilter.getViewed());
        }

        if (feedItemDalFilter.getApproved() != null) {
            select.where("approved", feedItemDalFilter.getApproved());
        }

        if (feedItemDalFilter.getReposted() != null) {
            select.where("reposted", feedItemDalFilter.getReposted());
        }

        if (feedItemDalFilter.getSent() != null) {
            select.where("sent", feedItemDalFilter.getSent());
        }
    }
}