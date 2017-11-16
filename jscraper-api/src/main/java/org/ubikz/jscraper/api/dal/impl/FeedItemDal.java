package org.ubikz.jscraper.api.dal.impl;

import org.springframework.stereotype.Repository;
import org.ubikz.jscraper.api.dal.BaseDal;
import org.ubikz.jscraper.api.dal.model.filter.AbstractDalFilter;
import org.ubikz.jscraper.api.dal.model.filter.impl.FeedItemDalFilter;
import org.ubikz.jscraper.api.dal.model.request.AbstractDalRequest;
import org.ubikz.jscraper.api.dal.model.request.impl.FeedItemDalRequest;
import org.ubikz.jscraper.api.dal.model.request.impl.FeedItemTagDalRequest;
import org.ubikz.jscraper.database.DatabaseService;
import org.ubikz.jscraper.database.querybuilder.AbstractQuery;
import org.ubikz.jscraper.database.querybuilder.QueryBuilderService;
import org.ubikz.jscraper.database.querybuilder.impl.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FeedItemDal extends BaseDal {

    public static final String COLUMN_FEED_ID = "feed_id";
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_COMMENT = "comment";
    public static final String COLUMN_CHECKSUM = "checksum";
    public static final String COLUMN_VIEWED = "viewed";
    public static final String COLUMN_APPROVED = "approved";
    public static final String COLUMN_REPOSTED = "reposted";
    public static final String COLUMN_SENT = "sent";
    public static final String ON_FI_ID_FIT_FEED_ITEM_ID = "fi.id = fit.feed_item_id";
    public static final String ALIAS_FI_ID = "fi.id";
    public static final String TABLE_FEED_ITEM_TAG = "feed_item_tag";

    /**
     * @param databaseService
     */
    public FeedItemDal(DatabaseService databaseService) {
        super(databaseService);
        this.tableName = "feed_item";
    }

    /**
     * @param requestList
     * @return
     */
    @Override
    public List<Object> createAll(List<AbstractDalRequest> requestList) {
        QueryBuilderService qb = new QueryBuilderService();
        AbstractQuery insert = qb
                .insert(this.tableName)
                .values(this.parseRequestList(requestList, true))
                .onConflict()
                .onDoNothing()
                .returning("id");

        return this.insertMultiple(insert);
    }

    @Override
    protected Select getBaseSelect(AbstractDalFilter filter, boolean isCount) {
        QueryBuilderService qb = new QueryBuilderService();
        AbstractQuery select = qb
                .select("fi.*")
                .from(this.tableName, "fi")
                .aliases(new HashMap<String, String>() {{
                    put("id", ALIAS_FI_ID);
                    put("date", "fi.date");
                }});

        this.parseFilter(filter, select, isCount);
        return (Select) select;
    }

    /**
     * @param requestList
     * @return
     */
    public int createTags(List<FeedItemTagDalRequest> requestList) {
        QueryBuilderService qb = new QueryBuilderService();

        AbstractQuery insert = qb
                .insert(TABLE_FEED_ITEM_TAG)
                .values(this.parseFeedItemTagRequest(requestList))
                .onConflict()
                .onDoNothing();

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
            values.put(COLUMN_FEED_ID, feedItemDalRequest.getFeedId());
        }
        
        if (feedItemDalRequest.getUrl() != null) {
            values.put(COLUMN_URL, feedItemDalRequest.getUrl());
        }

        if (feedItemDalRequest.getComment() != null) {
            values.put(COLUMN_COMMENT, feedItemDalRequest.getComment());
        }

        if (feedItemDalRequest.getChecksum() != null) {
            values.put(COLUMN_CHECKSUM, feedItemDalRequest.getChecksum());
        }

        if (feedItemDalRequest.getViewed() != null) {
            values.put(COLUMN_VIEWED, feedItemDalRequest.getViewed());
        }

        if (feedItemDalRequest.getApproved() != null) {
            values.put(COLUMN_APPROVED, feedItemDalRequest.getApproved());
        }

        if (feedItemDalRequest.getReposted() != null) {
            values.put(COLUMN_REPOSTED, feedItemDalRequest.getReposted());
        }

        if (feedItemDalRequest.getSent() != null) {
            values.put(COLUMN_SENT, feedItemDalRequest.getSent());
        }

        return values;
    }

    /**
     * @param filter
     * @return
     */
    @Override
    protected void parseFilter(AbstractDalFilter filter, AbstractQuery aSelect, boolean isCount) {
        Select select = (Select) aSelect;
        FeedItemDalFilter feedItemDalFilter = (FeedItemDalFilter) filter;
        super.parseFilter(feedItemDalFilter, select, isCount);

        if (!isCount) {
            select.addColumn("string_agg(DISTINCT fit.tag_id::character varying, ',') AS tags")
                    .orderBy(ALIAS_FI_ID)
                    .groupBy(ALIAS_FI_ID);
        } else {
            select.columns("COUNT(DISTINCT fi.id)");
        }

        if (feedItemDalFilter.getTagNames() != null && feedItemDalFilter.getTagNames().size() > 0) {
            select.join(TABLE_FEED_ITEM_TAG, "fit", ON_FI_ID_FIT_FEED_ITEM_ID)
                    .join("tag", "t", "fit.tag_id = t.id");

            for (String tag : feedItemDalFilter.getTagNames()) {
                select.orWhere("t.label", "LIKE", "%" + tag + "%");
            }
        } else {
            select.joinLeft(TABLE_FEED_ITEM_TAG, "fit", ON_FI_ID_FIT_FEED_ITEM_ID);
        }

        if (feedItemDalFilter.getUrl() != null) {
            select.where(COLUMN_URL, feedItemDalFilter.getUrl());
        }

        if (feedItemDalFilter.getFeedId() != null) {
            select.where(COLUMN_FEED_ID, feedItemDalFilter.getUrl());
        }

        if (feedItemDalFilter.getUrl() != null) {
            select.where(COLUMN_URL, feedItemDalFilter.getUrl());
        }

        if (feedItemDalFilter.getChecksum() != null) {
            select.where(COLUMN_CHECKSUM, feedItemDalFilter.getChecksum());
        }

        if (feedItemDalFilter.getViewed() != null) {
            select.where(COLUMN_VIEWED, feedItemDalFilter.getViewed());
        }

        if (feedItemDalFilter.getApproved() != null) {
            select.where(COLUMN_APPROVED, feedItemDalFilter.getApproved());
        }

        if (feedItemDalFilter.getReposted() != null) {
            select.where(COLUMN_REPOSTED, feedItemDalFilter.getReposted());
        }

        if (feedItemDalFilter.getSent() != null) {
            select.where(COLUMN_SENT, feedItemDalFilter.getSent());
        }
    }
}