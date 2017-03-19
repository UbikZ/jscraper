package com.ubikz.scraper.core.app.service;

import com.ubikz.scraper.core.app.entity.FeedProhibitedEntity;
import com.ubikz.scraper.core.app.entity.filter.AbstractEntityFilter;
import com.ubikz.scraper.core.app.entity.filter.FeedProhibitedEntityFilter;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import com.ubikz.scraper.core.app.entity.request.FeedProhibitedEntityRequest;
import com.ubikz.scraper.core.app.service.filter.AbstractServiceFilter;
import com.ubikz.scraper.core.app.service.filter.FeedProhibitedServiceFilter;
import com.ubikz.scraper.core.app.service.request.AbstractServiceRequest;
import com.ubikz.scraper.core.app.service.request.FeedProhibitedServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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