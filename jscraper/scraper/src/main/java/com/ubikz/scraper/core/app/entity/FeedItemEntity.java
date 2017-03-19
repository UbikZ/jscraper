package com.ubikz.scraper.core.app.entity;

import com.ubikz.scraper.core.app.dal.FeedItemDal;
import com.ubikz.scraper.core.app.dal.TagDal;
import com.ubikz.scraper.core.app.dal.filter.AbstractDalFilter;
import com.ubikz.scraper.core.app.dal.filter.FeedItemDalFilter;
import com.ubikz.scraper.core.app.dal.request.AbstractDalRequest;
import com.ubikz.scraper.core.app.dal.request.FeedItemDalRequest;
import com.ubikz.scraper.core.app.dal.request.FeedItemTagDalRequest;
import com.ubikz.scraper.core.app.dto.AbstractDto;
import com.ubikz.scraper.core.app.entity.filter.AbstractEntityFilter;
import com.ubikz.scraper.core.app.entity.filter.FeedItemEntityFilter;
import com.ubikz.scraper.core.app.entity.helper.FeedItemEntityHelper;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import com.ubikz.scraper.core.app.entity.request.FeedItemEntityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class FeedItemEntity extends AbstractEntity {
    @Autowired
    public FeedItemEntity(FeedItemDal feedItemDal) {
        this.dal = feedItemDal;
        this.helper = new FeedItemEntityHelper();
    }

    /**
     * @param request
     * @return
     */
    @Transactional
    public int createAllWithTags(FeedItemEntityRequest request) {
        int createdId = this.dal.create(this.parseEntityToDalRequest(request));

        if (request.getTagIds() != null) {
            ((FeedItemDal) this.dal).createTags(this.parseEntityToTagDalRequest(createdId, request));
        }

        return createdId;
    }

    @Override
    protected void computeLazyLoading(List<AbstractDto> dtoList) {
    }

    /**
     * @param id
     * @param request
     * @return
     */
    private List<FeedItemTagDalRequest> parseEntityToTagDalRequest(int id, FeedItemEntityRequest request) {
        List<FeedItemTagDalRequest> tagRequestList = new ArrayList<>();

        for (Integer tagId : request.getTagIds()) {
            FeedItemTagDalRequest feedItemTagDalRequest = new FeedItemTagDalRequest();
            feedItemTagDalRequest.setFeedItemId(id);
            feedItemTagDalRequest.setTagId(tagId);
            tagRequestList.add(feedItemTagDalRequest);
        }

        return tagRequestList;
    }

    /**
     * @param request
     * @return
     */
    @Override
    protected AbstractDalRequest parseEntityToDalRequest(AbstractEntityRequest request) {
        FeedItemDalRequest feedItemDalRequest = new FeedItemDalRequest();
        FeedItemEntityRequest feedItemEntityRequest = (FeedItemEntityRequest) request;

        feedItemDalRequest = (FeedItemDalRequest) this.parseBaseEntityToDalRequest(feedItemEntityRequest, feedItemDalRequest);
        feedItemDalRequest.setFeedId(feedItemEntityRequest.getFeedId());
        feedItemDalRequest.setUrl(feedItemEntityRequest.getUrl());
        feedItemDalRequest.setChecksum(feedItemEntityRequest.getChecksum());
        feedItemDalRequest.setApproved(feedItemEntityRequest.getApproved());
        feedItemDalRequest.setReposted(feedItemEntityRequest.getReposted());
        feedItemDalRequest.setViewed(feedItemEntityRequest.getViewed());
        feedItemDalRequest.setSent(feedItemEntityRequest.getReposted());

        return feedItemDalRequest;
    }

    /**
     * @param filter
     * @return
     */
    @Override
    protected AbstractDalFilter parseEntityToDalFilter(AbstractEntityFilter filter) {
        FeedItemDalFilter feedItemDalFilter = new FeedItemDalFilter();
        FeedItemEntityFilter feedItemEntityFilter = (FeedItemEntityFilter) filter;

        feedItemDalFilter = (FeedItemDalFilter) this.parseBaseEntityToDalFilter(feedItemEntityFilter, feedItemDalFilter);

        feedItemDalFilter.setFeedId(feedItemEntityFilter.getFeedId());
        feedItemDalFilter.setUrl(feedItemEntityFilter.getUrl());
        feedItemDalFilter.setChecksum(feedItemEntityFilter.getChecksum());
        feedItemDalFilter.setApproved(feedItemEntityFilter.getApproved());
        feedItemDalFilter.setReposted(feedItemEntityFilter.getReposted());
        feedItemDalFilter.setViewed(feedItemEntityFilter.getViewed());
        feedItemDalFilter.setSent(feedItemEntityFilter.getReposted());
        feedItemDalFilter.setTagIds(feedItemDalFilter.getTagIds());

        return feedItemDalFilter;
    }
}