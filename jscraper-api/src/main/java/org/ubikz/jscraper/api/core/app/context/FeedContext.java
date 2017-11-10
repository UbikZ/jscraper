package org.ubikz.jscraper.api.core.app.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.controller.filter.AbstractFilterBody;
import org.ubikz.jscraper.api.controller.filter.FeedFilterBody;
import org.ubikz.jscraper.api.controller.request.AbstractRequestBody;
import org.ubikz.jscraper.api.controller.request.FeedRequestBody;
import org.ubikz.jscraper.api.core.app.service.FeedService;
import org.ubikz.jscraper.api.core.app.service.filter.AbstractServiceFilter;
import org.ubikz.jscraper.api.core.app.service.filter.FeedServiceFilter;
import org.ubikz.jscraper.api.core.app.service.request.AbstractServiceRequest;
import org.ubikz.jscraper.api.core.app.service.request.FeedServiceRequest;

@Component
public class FeedContext extends AbstractContext {
    @Autowired
    public FeedContext(FeedService feedService) {
        this.service = feedService;
        this.serviceRequest = new FeedServiceRequest();
        this.serviceFilter = new FeedServiceFilter();
        this.filterBody = new FeedFilterBody();

        CREATED = 10;
        UPDATED = 11;
        GET_ONE = 12;
        GET_ALL = 13;
        DELETE = 14;
    }

    @Override
    protected AbstractServiceRequest parseRequest(AbstractRequestBody data, AbstractServiceRequest request) {
        FeedRequestBody requestBody = (FeedRequestBody) data;
        FeedServiceRequest serviceRequest = (FeedServiceRequest) this.parseBaseRequest(requestBody, request);
        serviceRequest.setUrl(requestBody.getUrl());
        serviceRequest.setFeedTypeId(requestBody.getFeedTypeId());

        return serviceRequest;
    }

    @Override
    protected AbstractServiceFilter parseFilter(AbstractFilterBody data, AbstractServiceFilter filter) throws Exception {
        return this.parseBaseFilter(data, filter);
    }
}