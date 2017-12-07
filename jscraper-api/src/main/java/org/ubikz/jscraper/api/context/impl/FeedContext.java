package org.ubikz.jscraper.api.context.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.context.BaseContext;
import org.ubikz.jscraper.api.controller.model.request.BaseRequestBody;
import org.ubikz.jscraper.api.controller.model.request.impl.FeedRequestBody;
import org.ubikz.jscraper.api.service.impl.FeedService;
import org.ubikz.jscraper.api.service.model.filter.impl.FeedServiceFilter;
import org.ubikz.jscraper.api.service.model.request.impl.FeedServiceRequest;

@Component
public class FeedContext extends BaseContext<FeedService, FeedServiceRequest, FeedServiceFilter> {
    @Autowired
    public FeedContext(FeedService service) {
        this.service = service;
        this.serviceRequest = new FeedServiceRequest();
        this.serviceFilter = new FeedServiceFilter();
    }

    @Override
    protected <T extends BaseRequestBody> void parseRequest(T data) {
        super.parseRequest(data);
        FeedRequestBody requestBody = (FeedRequestBody) data;
        serviceRequest.setUrl(requestBody.getUrl());
        serviceRequest.setFeedTypeId(requestBody.getFeedTypeId());
    }
}
