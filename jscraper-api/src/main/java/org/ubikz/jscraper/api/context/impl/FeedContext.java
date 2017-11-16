package org.ubikz.jscraper.api.context.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.context.BaseContext;
import org.ubikz.jscraper.api.controller.model.filter.BaseFilterBody;
import org.ubikz.jscraper.api.controller.model.filter.impl.FeedFilterBody;
import org.ubikz.jscraper.api.controller.model.request.BaseRequestBody;
import org.ubikz.jscraper.api.controller.model.request.impl.FeedRequestBody;
import org.ubikz.jscraper.api.service.impl.FeedService;
import org.ubikz.jscraper.api.service.model.filter.BaseServiceFilter;
import org.ubikz.jscraper.api.service.model.filter.impl.FeedServiceFilter;
import org.ubikz.jscraper.api.service.model.request.BaseServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.FeedServiceRequest;

@Component
public class FeedContext extends BaseContext {
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
    protected BaseServiceRequest parseRequest(BaseRequestBody data, BaseServiceRequest request) {
        FeedRequestBody requestBody = (FeedRequestBody) data;
        FeedServiceRequest serviceRequest = (FeedServiceRequest) parseBaseRequest(requestBody, request);
        serviceRequest.setUrl(requestBody.getUrl());
        serviceRequest.setFeedTypeId(requestBody.getFeedTypeId());

        return serviceRequest;
    }

    @Override
    protected BaseServiceFilter parseFilter(BaseFilterBody data, BaseServiceFilter filter) throws Exception {
        return parseBaseFilter(data, filter);
    }
}