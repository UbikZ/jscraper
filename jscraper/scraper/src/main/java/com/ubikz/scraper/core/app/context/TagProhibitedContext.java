package com.ubikz.scraper.core.app.context;

import com.ubikz.scraper.api.controller.filter.AbstractFilterBody;
import com.ubikz.scraper.api.controller.filter.TagProhibitedFilterBody;
import com.ubikz.scraper.api.controller.request.AbstractRequestBody;
import com.ubikz.scraper.core.app.service.TagProhibitedService;
import com.ubikz.scraper.core.app.service.filter.AbstractServiceFilter;
import com.ubikz.scraper.core.app.service.filter.TagProhibitedServiceFilter;
import com.ubikz.scraper.core.app.service.request.AbstractServiceRequest;
import com.ubikz.scraper.core.app.service.request.TagProhibitedServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TagProhibitedContext extends AbstractContext {
    @Autowired
    public TagProhibitedContext(TagProhibitedService tagProhibitedService) {
        this.service = tagProhibitedService;
        this.serviceRequest = new TagProhibitedServiceRequest();
        this.serviceFilter = new TagProhibitedServiceFilter();
        this.filterBody = new TagProhibitedFilterBody();

        this.CREATED = 60;
        this.UPDATED = 61;
        this.GET_ONE = 62;
        this.GET_ALL = 63;
        this.DELETE = 64;
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