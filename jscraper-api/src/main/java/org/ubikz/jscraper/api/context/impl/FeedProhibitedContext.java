package org.ubikz.jscraper.api.context.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.context.BaseContext;
import org.ubikz.jscraper.api.service.impl.FeedProhibitedService;
import org.ubikz.jscraper.api.service.model.filter.impl.FeedProhibitedServiceFilter;
import org.ubikz.jscraper.api.service.model.request.impl.FeedProhibitedServiceRequest;

@Component
public class FeedProhibitedContext extends BaseContext<FeedProhibitedService, FeedProhibitedServiceRequest, FeedProhibitedServiceFilter> {
    @Autowired
    public FeedProhibitedContext(FeedProhibitedService service) {
        this.service = service;
        this.serviceRequest = new FeedProhibitedServiceRequest();
        this.serviceFilter = new FeedProhibitedServiceFilter();
    }
}
