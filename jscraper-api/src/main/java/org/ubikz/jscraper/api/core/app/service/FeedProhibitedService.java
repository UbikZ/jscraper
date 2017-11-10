package org.ubikz.jscraper.api.core.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.core.app.entity.FeedProhibitedEntity;
import org.ubikz.jscraper.api.core.app.entity.filter.AbstractEntityFilter;
import org.ubikz.jscraper.api.core.app.entity.filter.FeedProhibitedEntityFilter;
import org.ubikz.jscraper.api.core.app.entity.request.AbstractEntityRequest;
import org.ubikz.jscraper.api.core.app.entity.request.FeedProhibitedEntityRequest;
import org.ubikz.jscraper.api.core.app.service.filter.AbstractServiceFilter;
import org.ubikz.jscraper.api.core.app.service.filter.FeedProhibitedServiceFilter;
import org.ubikz.jscraper.api.core.app.service.request.AbstractServiceRequest;
import org.ubikz.jscraper.api.core.app.service.request.FeedProhibitedServiceRequest;

@Component
public class FeedProhibitedService extends AbstractService {
    @Autowired
    public FeedProhibitedService(FeedProhibitedEntity feedProhibitedEntity) {
        this.entity = feedProhibitedEntity;
    }

    @Override
    protected AbstractEntityRequest parseServiceToEntityRequest(AbstractServiceRequest request) {
        FeedProhibitedEntityRequest feedProhibitedEntityRequest = new FeedProhibitedEntityRequest();
        FeedProhibitedServiceRequest feedProhibitedServiceRequest = (FeedProhibitedServiceRequest) request;

        this.parseBaseServiceToEntityRequest(feedProhibitedServiceRequest, feedProhibitedEntityRequest);

        return feedProhibitedEntityRequest;
    }

    @Override
    protected AbstractEntityFilter parseServiceToEntityFilter(AbstractServiceFilter filter) {
        FeedProhibitedEntityFilter feedProhibitedEntityFilter = new FeedProhibitedEntityFilter();
        FeedProhibitedServiceFilter feedProhibitedServiceFilter = (FeedProhibitedServiceFilter) filter;

        this.parseBaseServiceToEntityFilter(feedProhibitedServiceFilter, feedProhibitedEntityFilter);

        return feedProhibitedEntityFilter;
    }
}