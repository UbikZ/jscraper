package org.ubikz.jscraper.api.context.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.context.BaseContext;
import org.ubikz.jscraper.api.controller.model.filter.BaseFilterBody;
import org.ubikz.jscraper.api.controller.model.filter.impl.FeedTypeFilterBody;
import org.ubikz.jscraper.api.controller.model.request.BaseRequestBody;
import org.ubikz.jscraper.api.controller.model.request.impl.FeedTypeRequestBody;
import org.ubikz.jscraper.api.service.impl.FeedTypeService;
import org.ubikz.jscraper.api.service.model.filter.BaseServiceFilter;
import org.ubikz.jscraper.api.service.model.filter.impl.FeedTypeServiceFilter;
import org.ubikz.jscraper.api.service.model.request.BaseServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.FeedTypeServiceRequest;

@Component
public class FeedTypeContext extends BaseContext {
    @Autowired
    public FeedTypeContext(FeedTypeService feedTypeService) {
        this.service = feedTypeService;
        this.serviceRequest = new FeedTypeServiceRequest();
        this.serviceFilter = new FeedTypeServiceFilter();
        this.filterBody = new FeedTypeFilterBody();

        CREATED = 20;
        UPDATED = 21;
        GET_ONE = 22;
        GET_ALL = 23;
        DELETE = 24;
    }

    @Override
    protected BaseServiceRequest parseRequest(BaseRequestBody data, BaseServiceRequest request) {
        FeedTypeRequestBody requestBody = (FeedTypeRequestBody) data;
        FeedTypeServiceRequest serviceRequest = (FeedTypeServiceRequest) parseBaseRequest(requestBody, request);
        serviceRequest.setUrlRegex(requestBody.getUrlRegex());
        serviceRequest.setContentRegex(requestBody.getContentRegex());

        return serviceRequest;
    }

    @Override
    protected BaseServiceFilter parseFilter(BaseFilterBody data, BaseServiceFilter filter) throws Exception {
        return parseBaseFilter(data, filter);
    }
}