package org.ubikz.jscraper.api.context.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.context.AbstractContext;
import org.ubikz.jscraper.api.controller.model.filter.AbstractFilterBody;
import org.ubikz.jscraper.api.controller.model.filter.impl.FeedFilterBody;
import org.ubikz.jscraper.api.controller.model.request.AbstractRequestBody;
import org.ubikz.jscraper.api.controller.model.request.impl.FeedRequestBody;
import org.ubikz.jscraper.api.service.impl.FeedService;
import org.ubikz.jscraper.api.service.model.filter.AbstractServiceFilter;
import org.ubikz.jscraper.api.service.model.filter.impl.FeedServiceFilter;
import org.ubikz.jscraper.api.service.model.request.AbstractServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.FeedServiceRequest;

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
        FeedServiceRequest serviceRequest = (FeedServiceRequest) parseBaseRequest(requestBody, request);
        serviceRequest.setUrl(requestBody.getUrl());
        serviceRequest.setFeedTypeId(requestBody.getFeedTypeId());

        return serviceRequest;
    }

    @Override
    protected AbstractServiceFilter parseFilter(AbstractFilterBody data, AbstractServiceFilter filter) throws Exception {
        return parseBaseFilter(data, filter);
    }
}