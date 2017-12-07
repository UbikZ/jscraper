package org.ubikz.jscraper.api.context.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.context.BaseContext;
import org.ubikz.jscraper.api.controller.model.request.BaseRequestBody;
import org.ubikz.jscraper.api.controller.model.request.impl.FeedTypeRequestBody;
import org.ubikz.jscraper.api.service.impl.FeedTypeService;
import org.ubikz.jscraper.api.service.model.filter.impl.FeedTypeServiceFilter;
import org.ubikz.jscraper.api.service.model.request.impl.FeedTypeServiceRequest;

@Component
public class FeedTypeContext extends BaseContext<FeedTypeService, FeedTypeServiceRequest, FeedTypeServiceFilter> {
    @Autowired
    public FeedTypeContext(FeedTypeService service) {
        this.service = service;
        this.serviceRequest = new FeedTypeServiceRequest();
        this.serviceFilter = new FeedTypeServiceFilter();
    }

    @Override
    protected <T extends BaseRequestBody> void parseRequest(T data) {
        super.parseRequest(data);
        FeedTypeRequestBody requestBody = (FeedTypeRequestBody) data;
        serviceRequest.setUrlRegex(requestBody.getUrlRegex());
        serviceRequest.setContentRegex(requestBody.getContentRegex());
    }
}
