package com.ubikz.scraper.core.app.service;

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

@Component
public class FeedService extends AbstractService {
    @Autowired
    public FeedService(FeedEntity feedEntity) {
        this.entity = feedEntity;
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