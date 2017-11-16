package org.ubikz.jscraper.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.entity.impl.FeedEntity;
import org.ubikz.jscraper.api.entity.model.filter.AbstractEntityFilter;
import org.ubikz.jscraper.api.entity.model.filter.impl.FeedEntityFilter;
import org.ubikz.jscraper.api.entity.model.request.AbstractEntityRequest;
import org.ubikz.jscraper.api.entity.model.request.impl.FeedEntityRequest;
import org.ubikz.jscraper.api.service.BaseService;
import org.ubikz.jscraper.api.service.model.filter.BaseServiceFilter;
import org.ubikz.jscraper.api.service.model.filter.impl.FeedServiceFilter;
import org.ubikz.jscraper.api.service.model.request.BaseServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.FeedServiceRequest;

@Component
public class FeedService extends BaseService {
    @Autowired
    public FeedService(FeedEntity feedEntity) {
        this.entity = feedEntity;
    }

    @Override
    protected AbstractEntityRequest parseServiceToEntityRequest(BaseServiceRequest request) {
        FeedEntityRequest feedEntityRequest = new FeedEntityRequest();
        FeedServiceRequest feedServiceRequest = (FeedServiceRequest) request;

        this.parseBaseServiceToEntityRequest(feedServiceRequest, feedEntityRequest);
        feedEntityRequest.setUrl(feedServiceRequest.getUrl());
        feedEntityRequest.setFeedTypeId(feedServiceRequest.getFeedTypeId());

        return feedEntityRequest;
    }

    @Override
    protected AbstractEntityFilter parseServiceToEntityFilter(BaseServiceFilter filter) {
        FeedEntityFilter feedEntityFilter = new FeedEntityFilter();
        FeedServiceFilter feedServiceFilter = (FeedServiceFilter) filter;

        this.parseBaseServiceToEntityFilter(feedServiceFilter, feedEntityFilter);
        feedEntityFilter.setUrl(feedServiceFilter.getUrl());

        return feedEntityFilter;
    }
}