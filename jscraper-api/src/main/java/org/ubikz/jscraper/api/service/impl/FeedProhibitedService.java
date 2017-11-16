package org.ubikz.jscraper.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.entity.impl.FeedProhibitedEntity;
import org.ubikz.jscraper.api.entity.model.filter.AbstractEntityFilter;
import org.ubikz.jscraper.api.entity.model.filter.impl.FeedProhibitedEntityFilter;
import org.ubikz.jscraper.api.entity.model.request.AbstractEntityRequest;
import org.ubikz.jscraper.api.entity.model.request.impl.FeedProhibitedEntityRequest;
import org.ubikz.jscraper.api.service.BaseService;
import org.ubikz.jscraper.api.service.model.filter.BaseServiceFilter;
import org.ubikz.jscraper.api.service.model.filter.impl.FeedProhibitedServiceFilter;
import org.ubikz.jscraper.api.service.model.request.BaseServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.FeedProhibitedServiceRequest;

@Component
public class FeedProhibitedService extends BaseService {
    @Autowired
    public FeedProhibitedService(FeedProhibitedEntity feedProhibitedEntity) {
        this.entity = feedProhibitedEntity;
    }

    @Override
    protected AbstractEntityRequest parseServiceToEntityRequest(BaseServiceRequest request) {
        FeedProhibitedEntityRequest feedProhibitedEntityRequest = new FeedProhibitedEntityRequest();
        FeedProhibitedServiceRequest feedProhibitedServiceRequest = (FeedProhibitedServiceRequest) request;

        this.parseBaseServiceToEntityRequest(feedProhibitedServiceRequest, feedProhibitedEntityRequest);

        return feedProhibitedEntityRequest;
    }

    @Override
    protected AbstractEntityFilter parseServiceToEntityFilter(BaseServiceFilter filter) {
        FeedProhibitedEntityFilter feedProhibitedEntityFilter = new FeedProhibitedEntityFilter();
        FeedProhibitedServiceFilter feedProhibitedServiceFilter = (FeedProhibitedServiceFilter) filter;

        this.parseBaseServiceToEntityFilter(feedProhibitedServiceFilter, feedProhibitedEntityFilter);

        return feedProhibitedEntityFilter;
    }
}