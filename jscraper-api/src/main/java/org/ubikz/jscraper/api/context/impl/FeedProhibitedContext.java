package org.ubikz.jscraper.api.context.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.context.BaseContext;
import org.ubikz.jscraper.api.controller.model.filter.BaseFilterBody;
import org.ubikz.jscraper.api.controller.model.filter.impl.FeedProhibitedFilterBody;
import org.ubikz.jscraper.api.controller.model.request.BaseRequestBody;
import org.ubikz.jscraper.api.service.impl.FeedProhibitedService;
import org.ubikz.jscraper.api.service.impl.FeedService;
import org.ubikz.jscraper.api.service.model.filter.BaseServiceFilter;
import org.ubikz.jscraper.api.service.model.filter.impl.FeedProhibitedServiceFilter;
import org.ubikz.jscraper.api.service.model.filter.impl.FeedServiceFilter;
import org.ubikz.jscraper.api.service.model.request.BaseServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.FeedProhibitedServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.FeedServiceRequest;

@Component
public class FeedProhibitedContext extends BaseContext<FeedProhibitedService, FeedProhibitedServiceRequest, FeedProhibitedServiceFilter> {
    @Autowired
    public FeedProhibitedContext(FeedProhibitedService service) {
        this.service = service;
        this.serviceRequest = new FeedProhibitedServiceRequest();
        this.serviceFilter = new FeedProhibitedServiceFilter();
    }
}