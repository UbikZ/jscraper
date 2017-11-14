package org.ubikz.jscraper.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.entity.FeedEntity;
import org.ubikz.jscraper.api.entity.model.filter.AbstractEntityFilter;
import org.ubikz.jscraper.api.entity.model.filter.impl.FeedEntityFilter;
import org.ubikz.jscraper.api.entity.model.request.AbstractEntityRequest;
import org.ubikz.jscraper.api.entity.model.request.impl.FeedEntityRequest;
import org.ubikz.jscraper.api.service.AbstractService;
import org.ubikz.jscraper.api.service.model.filter.AbstractServiceFilter;
import org.ubikz.jscraper.api.service.model.filter.impl.FeedServiceFilter;
import org.ubikz.jscraper.api.service.model.request.AbstractServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.FeedServiceRequest;

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