package org.ubikz.jscraper.api.core.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.core.app.entity.FeedEntity;
import org.ubikz.jscraper.api.core.app.entity.filter.AbstractEntityFilter;
import org.ubikz.jscraper.api.core.app.entity.filter.FeedEntityFilter;
import org.ubikz.jscraper.api.core.app.entity.request.AbstractEntityRequest;
import org.ubikz.jscraper.api.core.app.entity.request.FeedEntityRequest;
import org.ubikz.jscraper.api.core.app.service.filter.AbstractServiceFilter;
import org.ubikz.jscraper.api.core.app.service.filter.FeedServiceFilter;
import org.ubikz.jscraper.api.core.app.service.request.AbstractServiceRequest;
import org.ubikz.jscraper.api.core.app.service.request.FeedServiceRequest;

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