package com.ubikz.scraper.core.app.context;

import com.ubikz.scraper.api.controller.filter.AbstractFilterBody;
import com.ubikz.scraper.api.controller.filter.FeedFilterBody;
import com.ubikz.scraper.api.controller.request.AbstractRequestBody;
import com.ubikz.scraper.api.controller.request.FeedRequestBody;
import com.ubikz.scraper.core.app.service.FeedService;
import com.ubikz.scraper.core.app.service.filter.AbstractServiceFilter;
import com.ubikz.scraper.core.app.service.filter.FeedServiceFilter;
import com.ubikz.scraper.core.app.service.request.AbstractServiceRequest;
import com.ubikz.scraper.core.app.service.request.FeedServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeedContext extends AbstractContext {
    @Autowired
    public FeedContext(FeedService feedService) {
        this.service = feedService;
        this.serviceRequest = new FeedServiceRequest();
        this.serviceFilter = new FeedServiceFilter();
        this.filterBody = new FeedFilterBody();

        this.CREATED = 10;
        this.UPDATED = 11;
        this.GET_ONE = 12;
        this.GET_ALL = 13;
        this.DELETE = 14;
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