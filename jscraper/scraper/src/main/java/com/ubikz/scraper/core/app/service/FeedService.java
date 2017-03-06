package com.ubikz.scraper.core.app.service;

import com.ubikz.scraper.core.app.dto.FeedDto;
import com.ubikz.scraper.core.app.entity.FeedEntity;
import com.ubikz.scraper.core.app.entity.filter.AbstractEntityFilter;
import com.ubikz.scraper.core.app.entity.filter.FeedEntityFilter;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import com.ubikz.scraper.core.app.entity.request.FeedEntityRequest;
import com.ubikz.scraper.core.app.service.filter.AbstractServiceFilter;
import com.ubikz.scraper.core.app.service.filter.FeedServiceFilter;
import com.ubikz.scraper.core.app.service.request.AbstractServiceRequest;
import com.ubikz.scraper.core.app.service.request.FeedServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeedService extends AbstractService {
    private FeedEntity feedEntity;

    @Autowired
    public FeedService(FeedEntity feedEntity) {
        this.feedEntity = feedEntity;
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public List<FeedDto> getAllFeeds(FeedServiceFilter filter) throws Exception {
        return this.feedEntity.getAllFeeds(
                (FeedEntityFilter) this.parseServiceToEntityFilter(filter)
        );
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public FeedDto getOneFeed(FeedServiceFilter filter) throws Exception {
        return this.feedEntity.getOneFeed(
                (FeedEntityFilter) this.parseServiceToEntityFilter(filter)
        );
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public int delete(FeedServiceFilter filter) throws Exception {
        return this.feedEntity.deleteFeed(
                (FeedEntityFilter) this.parseServiceToEntityFilter(filter)
        );
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public int createFeed(FeedServiceRequest request) throws Exception {
        return this.feedEntity.createFeed((FeedEntityRequest) this.parseServiceToEntityRequest(request));
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public int updateFeed(FeedServiceRequest request) throws Exception {
        return this.feedEntity.updateFeed((FeedEntityRequest) this.parseServiceToEntityRequest(request));
    }


    @Override
    protected AbstractEntityRequest parseServiceToEntityRequest(AbstractServiceRequest request) {
        FeedEntityRequest feedEntityRequest = new FeedEntityRequest();
        FeedServiceRequest feedServiceRequest = (FeedServiceRequest) request;

        this.parseBaseServiceToEntityRequest(feedServiceRequest, feedEntityRequest);
        feedEntityRequest.setUrl(feedServiceRequest.getUrl());
        feedEntityRequest.setFeedTypeId(feedServiceRequest.getFeedTypeId());

        return feedEntityRequest;
    }

    @Override
    protected AbstractEntityFilter parseServiceToEntityFilter(AbstractServiceFilter filter) {
        FeedEntityFilter feedEntityFilter = new FeedEntityFilter();
        FeedServiceFilter feedServiceFilter = (FeedServiceFilter) filter;

        this.parseBaseServiceToEntityFilter(feedServiceFilter, feedEntityFilter);
        feedEntityFilter.setUrl(feedServiceFilter.getUrl());

        return feedEntityFilter;
    }
}