package com.ubikz.scraper.core.app.service;

import com.ubikz.scraper.core.app.dto.FeedItemDto;
import com.ubikz.scraper.core.app.entity.FeedItemEntity;
import com.ubikz.scraper.core.app.entity.filter.AbstractEntityFilter;
import com.ubikz.scraper.core.app.entity.filter.FeedItemEntityFilter;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import com.ubikz.scraper.core.app.entity.request.FeedItemEntityRequest;
import com.ubikz.scraper.core.app.service.filter.AbstractServiceFilter;
import com.ubikz.scraper.core.app.service.filter.FeedItemServiceFilter;
import com.ubikz.scraper.core.app.service.request.AbstractServiceRequest;
import com.ubikz.scraper.core.app.service.request.FeedItemServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeedItemService extends AbstractService {
    private FeedItemEntity feedItemEntity;

    @Autowired
    public FeedItemService(FeedItemEntity feedItemEntity) {
        this.feedItemEntity = feedItemEntity;
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public List<FeedItemDto> getAllFeedItems(FeedItemServiceFilter filter) throws Exception {
        return this.feedItemEntity.getAllFeedItems(
                (FeedItemEntityFilter) this.parseServiceToEntityFilter(filter)
        );
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public FeedItemDto getOneFeedItem(FeedItemServiceFilter filter) throws Exception {
        return this.feedItemEntity.getOneFeedItem(
                (FeedItemEntityFilter) this.parseServiceToEntityFilter(filter)
        );
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public int delete(FeedItemServiceFilter filter) throws Exception {
        return this.feedItemEntity.deleteFeedItem(
                (FeedItemEntityFilter) this.parseServiceToEntityFilter(filter)
        );
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public int createFeedItem(FeedItemServiceRequest request) throws Exception {
        return this.feedItemEntity.createFeedItem((FeedItemEntityRequest) this.parseServiceToEntityRequest(request));
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public int updateFeedItem(FeedItemServiceRequest request) throws Exception {
        return this.feedItemEntity.updateFeedItem((FeedItemEntityRequest) this.parseServiceToEntityRequest(request));
    }

    /**
     * @param request
     * @return
     */
    @Override
    protected AbstractEntityRequest parseServiceToEntityRequest(AbstractServiceRequest request) {
        FeedItemEntityRequest feedItemEntityRequest = new FeedItemEntityRequest();
        FeedItemServiceRequest feedItemServiceRequest = (FeedItemServiceRequest) request;

        this.parseBaseServiceToEntityRequest(feedItemServiceRequest, feedItemEntityRequest);
        feedItemEntityRequest.setUrl(feedItemServiceRequest.getUrl());
        feedItemEntityRequest.setFeedId(feedItemServiceRequest.getFeedId());
        feedItemEntityRequest.setFeedTypeId(feedItemServiceRequest.getFeedTypeId());
        feedItemEntityRequest.setChecksum(feedItemServiceRequest.getChecksum());
        feedItemEntityRequest.setTagIds(feedItemServiceRequest.getTagIds());
        feedItemEntityRequest.setViewed(feedItemServiceRequest.getViewed());
        feedItemEntityRequest.setApproved(feedItemServiceRequest.getApproved());
        feedItemEntityRequest.setReposted(feedItemServiceRequest.getReposted());
        feedItemEntityRequest.setSent(feedItemServiceRequest.getSent());

        return feedItemEntityRequest;
    }

    /**
     * @param filter
     * @return
     */
    @Override
    protected AbstractEntityFilter parseServiceToEntityFilter(AbstractServiceFilter filter) {
        FeedItemEntityFilter feedItemEntityFilter = new FeedItemEntityFilter();
        FeedItemServiceFilter feedItemServiceFilter = (FeedItemServiceFilter) filter;

        this.parseBaseServiceToEntityFilter(feedItemServiceFilter, feedItemEntityFilter);
        feedItemEntityFilter.setUrl(feedItemServiceFilter.getUrl());
        feedItemEntityFilter.setFeedId(feedItemServiceFilter.getFeedId());
        feedItemEntityFilter.setFeedTypeId(feedItemServiceFilter.getFeedTypeId());
        feedItemEntityFilter.setChecksum(feedItemServiceFilter.getChecksum());
        feedItemEntityFilter.setViewed(feedItemServiceFilter.getViewed());
        feedItemEntityFilter.setApproved(feedItemServiceFilter.getApproved());
        feedItemEntityFilter.setReposted(feedItemServiceFilter.getReposted());
        feedItemEntityFilter.setSent(feedItemServiceFilter.getSent());

        return feedItemEntityFilter;
    }
}