package com.ubikz.scraper.core.app.context;

import com.ubikz.scraper.api.controller.filter.AbstractFilterBody;
import com.ubikz.scraper.api.controller.filter.FeedTypeFilterBody;
import com.ubikz.scraper.api.controller.request.AbstractRequestBody;
import com.ubikz.scraper.api.controller.request.FeedTypeRequestBody;
import com.ubikz.scraper.core.app.service.FeedTypeService;
import com.ubikz.scraper.core.app.service.filter.AbstractServiceFilter;
import com.ubikz.scraper.core.app.service.filter.FeedTypeServiceFilter;
import com.ubikz.scraper.core.app.service.request.AbstractServiceRequest;
import com.ubikz.scraper.core.app.service.request.FeedTypeServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeedTypeContext extends AbstractContext {
    @Autowired
    public FeedTypeContext(FeedTypeService feedTypeService) {
        this.service = feedTypeService;
        this.serviceRequest = new FeedTypeServiceRequest();
        this.serviceFilter = new FeedTypeServiceFilter();
        this.filterBody = new FeedTypeFilterBody();

        this.CREATED = 20;
        this.UPDATED = 21;
        this.GET_ONE = 22;
        this.GET_ALL = 23;
        this.DELETE = 24;
    }

    @Override
    protected AbstractServiceRequest parseRequest(AbstractRequestBody data, AbstractServiceRequest request) {
        FeedTypeRequestBody requestBody = (FeedTypeRequestBody) data;
        FeedTypeServiceRequest serviceRequest = (FeedTypeServiceRequest) this.parseBaseRequest(requestBody, request);
        serviceRequest.setUrlRegex(requestBody.getUrlRegex());
        serviceRequest.setContentRegex(requestBody.getContentRegex());

        return serviceRequest;
    }

    @Override
    protected AbstractServiceFilter parseFilter(AbstractFilterBody data, AbstractServiceFilter filter) {
        return this.parseBaseFilter(data, filter);
    }
}