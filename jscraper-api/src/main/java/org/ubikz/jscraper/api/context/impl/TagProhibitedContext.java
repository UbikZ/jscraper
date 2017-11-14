package org.ubikz.jscraper.api.context.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.context.AbstractContext;
import org.ubikz.jscraper.api.controller.model.filter.AbstractFilterBody;
import org.ubikz.jscraper.api.controller.model.filter.impl.TagProhibitedFilterBody;
import org.ubikz.jscraper.api.controller.model.request.AbstractRequestBody;
import org.ubikz.jscraper.api.service.impl.TagProhibitedService;
import org.ubikz.jscraper.api.service.model.filter.AbstractServiceFilter;
import org.ubikz.jscraper.api.service.model.filter.impl.TagProhibitedServiceFilter;
import org.ubikz.jscraper.api.service.model.request.AbstractServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.TagProhibitedServiceRequest;

@Component
public class TagProhibitedContext extends AbstractContext {
    @Autowired
    public TagProhibitedContext(TagProhibitedService tagProhibitedService) {
        this.service = tagProhibitedService;
        this.serviceRequest = new TagProhibitedServiceRequest();
        this.serviceFilter = new TagProhibitedServiceFilter();
        this.filterBody = new TagProhibitedFilterBody();

        CREATED = 60;
        UPDATED = 61;
        GET_ONE = 62;
        GET_ALL = 63;
        DELETE = 64;
    }

    @Override
    protected AbstractServiceRequest parseRequest(AbstractRequestBody data, AbstractServiceRequest request) {
        return parseBaseRequest(data, request);
    }

    @Override
    protected AbstractServiceFilter parseFilter(AbstractFilterBody data, AbstractServiceFilter filter) throws Exception {
        return parseBaseFilter(data, filter);
    }
}