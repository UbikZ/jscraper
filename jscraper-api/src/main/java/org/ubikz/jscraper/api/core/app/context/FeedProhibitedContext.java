package org.ubikz.jscraper.api.core.app.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.controller.filter.AbstractFilterBody;
import org.ubikz.jscraper.api.controller.filter.FeedProhibitedFilterBody;
import org.ubikz.jscraper.api.controller.request.AbstractRequestBody;
import org.ubikz.jscraper.api.core.app.service.FeedProhibitedService;
import org.ubikz.jscraper.api.core.app.service.filter.AbstractServiceFilter;
import org.ubikz.jscraper.api.core.app.service.filter.FeedProhibitedServiceFilter;
import org.ubikz.jscraper.api.core.app.service.request.AbstractServiceRequest;
import org.ubikz.jscraper.api.core.app.service.request.FeedProhibitedServiceRequest;

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
        return this.parseBaseRequest(data, request);
    }

    @Override
    protected AbstractServiceFilter parseFilter(AbstractFilterBody data, AbstractServiceFilter filter) throws Exception {
        return this.parseBaseFilter(data, filter);
    }
}