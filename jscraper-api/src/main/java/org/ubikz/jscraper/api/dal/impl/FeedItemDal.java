package org.ubikz.jscraper.api.dal.impl;

import org.springframework.stereotype.Repository;
import org.ubikz.jscraper.api.dal.BaseDal;
import org.ubikz.jscraper.api.dal.model.filter.impl.FeedItemDalFilter;
import org.ubikz.jscraper.api.dal.model.request.impl.FeedItemDalRequest;
import org.ubikz.jscraper.api.dal.model.request.impl.FeedItemTagDalRequest;
import org.ubikz.jscraper.database.DatabaseService;
import org.ubikz.jscraper.database.querybuilder.impl.Select;
import org.ubikz.jscraper.database.reference.IFieldReference;
import org.ubikz.jscraper.database.reference.impl.OperatorReference;
import org.ubikz.jscraper.reference.table.TableReference;
import org.ubikz.jscraper.reference.table.field.CommonReference;
import org.ubikz.jscraper.reference.table.field.FeedItemReference;
import org.ubikz.jscraper.reference.table.field.FeedItemTagReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FeedItemDal extends BaseDal<FeedItemDalRequest, FeedItemDalFilter> {
    private static final String ALIAS_FI_ID = "fi.id";
    private static final String ALIAS_FIT_FEED_ITEM_ID = "fit.feed_item_id";

    public FeedItemDal(DatabaseService databaseService) {
        super(databaseService);
        this.table = TableReference.FEED_ITEM;
    }

    @Override
    public List<Object> createAll(List<FeedItemDalRequest> requestList) {
        return ds.insertMultiple(insert -> insert
                .table(table)
                .values(parseRequestList(requestList))
                .onConflict()
                .onDoNothing()
                .returning(CommonReference.ID)
        );
    }

    @Override
    protected Select getBaseSelect(FeedItemDalFilter filter, boolean isCount) {
        Select select = new Select()
                .column("fi.*")
                .from(table, "fi");

//                .alias(CommonReference.ID, ALIAS_FI_ID)
//                .alias(CommonReference.DATE, "fi.date");

        parseFilter(filter, select, isCount);

        return select;
    }

    public int createTags(List<FeedItemTagDalRequest> requestList) {
        return ds.insert(insert -> insert
                .table(TableReference.FEED_ITEM_TAG)
                .values(parseFeedItemTagRequest(requestList))
                .onConflict()
                .onDoNothing()
        );
    }

    private List<Map<IFieldReference, Object>> parseFeedItemTagRequest(List<FeedItemTagDalRequest> requestList) {
        List<Map<IFieldReference, Object>> valuesList = new ArrayList<>();

        for (FeedItemTagDalRequest request : requestList) {
            Map<IFieldReference, Object> values = new HashMap<>();

            if (request.getFeedItemId() != null) {
                values.put(FeedItemTagReference.FEED_ITEM_ID, request.getFeedItemId());
            }

            if (request.getTagId() != null) {
                values.put(FeedItemTagReference.TAG_ID, request.getTagId());
            }

            valuesList.add(values);
        }

        return valuesList;
    }

    @Override
    protected Map<IFieldReference, Object> parseRequest(FeedItemDalRequest request) {
        Map<IFieldReference, Object> values = super.parseRequest(request);

        if (request.getFeedId() != null) {
            values.put(FeedItemReference.FEED_ID, request.getFeedId());
        }

        if (request.getUrl() != null) {
            values.put(FeedItemReference.URL, request.getUrl());
        }

        if (request.getComment() != null) {
            values.put(FeedItemReference.COMMENT, request.getComment());
        }

        if (request.getChecksum() != null) {
            values.put(FeedItemReference.CHECKSUM, request.getChecksum());
        }

        if (request.getViewed() != null) {
            values.put(FeedItemReference.VIEWED, request.getViewed());
        }

        if (request.getApproved() != null) {
            values.put(FeedItemReference.APPROVED, request.getApproved());
        }

        if (request.getReposted() != null) {
            values.put(FeedItemReference.REPOSTED, request.getReposted());
        }

        if (request.getSent() != null) {
            values.put(FeedItemReference.SENT, request.getSent());
        }

        return values;
    }

    @Override
    protected void parseFilter(FeedItemDalFilter filter, Select select, boolean isCount) {
        super.parseFilter(filter, select, isCount);

        if (!isCount) {
            select.column("string_agg(DISTINCT fit.tag_id::character varying, ',') AS tags")
                    .orderBy(ALIAS_FI_ID)
                    .groupBy(ALIAS_FI_ID);
        } else {
            select.column("COUNT(DISTINCT fi.id)");
        }

        if (filter.getTagNames() != null && filter.getTagNames().size() > 0) {
            select.join(j -> j.set(TableReference.FEED_ITEM_TAG, "fit", ALIAS_FI_ID, ALIAS_FIT_FEED_ITEM_ID))
                    .join(j -> j.set(TableReference.TAG, "t", "fit.tag_id", "t.id"));

            for (String tag : filter.getTagNames()) {
                select.where(w -> w.or(p -> p.set("t.label", OperatorReference.LIKE, "%" + tag + "%")));
            }
        } else {
            select.joinLeft(j -> j.set(TableReference.FEED_ITEM_TAG, "fit", ALIAS_FI_ID, ALIAS_FIT_FEED_ITEM_ID));
        }

        if (filter.getUrl() != null) {
            select.where(w -> w.and(p -> p.set(FeedItemReference.URL, filter.getUrl())));
        }

        if (filter.getFeedId() != null) {
            select.where(w -> w.and(p -> p.set(FeedItemReference.FEED_ID, filter.getFeedId())));
        }

        if (filter.getChecksum() != null) {
            select.where(w -> w.and(p -> p.set(FeedItemReference.CHECKSUM, filter.getChecksum())));
        }

        if (filter.getViewed() != null) {
            select.where(w -> w.and(p -> p.set(FeedItemReference.VIEWED, filter.getViewed())));
        }

        if (filter.getApproved() != null) {
            select.where(w -> w.and(p -> p.set(FeedItemReference.APPROVED, filter.getApproved())));
        }

        if (filter.getReposted() != null) {
            select.where(w -> w.and(p -> p.set(FeedItemReference.REPOSTED, filter.getReposted())));
        }

        if (filter.getSent() != null) {
            select.where(w -> w.and(p -> p.set(FeedItemReference.SENT, filter.getSent())));
        }
    }
}
