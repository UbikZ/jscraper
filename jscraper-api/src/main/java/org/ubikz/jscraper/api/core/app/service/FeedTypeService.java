package org.ubikz.jscraper.api.core.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.core.app.entity.FeedTypeEntity;
import org.ubikz.jscraper.api.core.app.entity.filter.AbstractEntityFilter;
import org.ubikz.jscraper.api.core.app.entity.filter.FeedTypeEntityFilter;
import org.ubikz.jscraper.api.core.app.entity.request.AbstractEntityRequest;
import org.ubikz.jscraper.api.core.app.entity.request.FeedTypeEntityRequest;
import org.ubikz.jscraper.api.core.app.service.filter.AbstractServiceFilter;
import org.ubikz.jscraper.api.core.app.service.filter.FeedTypeServiceFilter;
import org.ubikz.jscraper.api.core.app.service.request.AbstractServiceRequest;
import org.ubikz.jscraper.api.core.app.service.request.FeedTypeServiceRequest;

@Component
public class FeedTypeService extends AbstractService {
    @Autowired
    public FeedTypeService(FeedTypeEntity feedTypeEntity) {
        this.entity = feedTypeEntity;
    }

    @Override
    protected AbstractEntityRequest parseServiceToEntityRequest(AbstractServiceRequest request) {
        FeedTypeEntityRequest feedTypeEntityRequest = new FeedTypeEntityRequest();
        FeedTypeServiceRequest feedTypeServiceRequest = (FeedTypeServiceRequest) request;

        this.parseBaseServiceToEntityRequest(feedTypeServiceRequest, feedTypeEntityRequest);
        feedTypeEntityRequest.setUrlRegex(feedTypeServiceRequest.getUrlRegex());
        feedTypeEntityRequest.setContentRegex(feedTypeServiceRequest.getContentRegex());

        return feedTypeEntityRequest;
    }

    @Override
    protected AbstractEntityFilter parseServiceToEntityFilter(AbstractServiceFilter filter) {
        FeedTypeEntityFilter feedTypeEntityFilter = new FeedTypeEntityFilter();
        FeedTypeServiceFilter feedTypeServiceFilter = (FeedTypeServiceFilter) filter;

        this.parseBaseServiceToEntityFilter(feedTypeServiceFilter, feedTypeEntityFilter);

        return feedTypeEntityFilter;
    }
}