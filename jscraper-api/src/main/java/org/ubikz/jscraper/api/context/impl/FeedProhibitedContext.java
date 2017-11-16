package org.ubikz.jscraper.api.context.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.context.BaseContext;
import org.ubikz.jscraper.api.controller.model.filter.BaseFilterBody;
import org.ubikz.jscraper.api.controller.model.filter.impl.FeedProhibitedFilterBody;
import org.ubikz.jscraper.api.controller.model.request.BaseRequestBody;
import org.ubikz.jscraper.api.service.impl.FeedProhibitedService;
import org.ubikz.jscraper.api.service.model.filter.BaseServiceFilter;
import org.ubikz.jscraper.api.service.model.filter.impl.FeedProhibitedServiceFilter;
import org.ubikz.jscraper.api.service.model.request.BaseServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.FeedProhibitedServiceRequest;

@Component
public class FeedProhibitedContext extends BaseContext {
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
    protected BaseServiceRequest parseRequest(BaseRequestBody data, BaseServiceRequest request) {
        return parseBaseRequest(data, request);
    }

    @Override
    protected BaseServiceFilter parseFilter(BaseFilterBody data, BaseServiceFilter filter) throws Exception {
        return parseBaseFilter(data, filter);
    }
}