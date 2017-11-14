package org.ubikz.jscraper.api.context.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.context.AbstractContext;
import org.ubikz.jscraper.api.controller.model.filter.AbstractFilterBody;
import org.ubikz.jscraper.api.controller.model.filter.impl.FeedProhibitedFilterBody;
import org.ubikz.jscraper.api.controller.model.request.AbstractRequestBody;
import org.ubikz.jscraper.api.service.impl.FeedProhibitedService;
import org.ubikz.jscraper.api.service.model.filter.AbstractServiceFilter;
import org.ubikz.jscraper.api.service.model.filter.impl.FeedProhibitedServiceFilter;
import org.ubikz.jscraper.api.service.model.request.AbstractServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.FeedProhibitedServiceRequest;

@Component
public class FeedProhibitedContext extends AbstractContext {
    @Autowired
    public FeedProhibitedContext(FeedProhibitedService feedProhibitedService) {
        this.service = feedProhibitedService;
        this.serviceRequest = new FeedProhibitedServiceRequest();
        this.serviceFilter = new FeedProhibitedServiceFilter();
        this.filterBody = new FeedProhibitedFilterBody();

        CREATED = 50;
        UPDATED = 51;
        GET_ONE = 52;
        GET_ALL = 52;
        DELETE = 53;
    }

    @Override
    protected AbstractServiceRequest parseRequest(AbstractRequestBody data, AbstractServiceRequest request) {
        return parseBaseRequest(data, request);
    }

    @Override
    protected AbstractServiceFilter parseFilter(AbstractFilterBody data, AbstractServiceFilter filter) throws Exception {
        return parseBaseFilter(data, filter);
    }
}