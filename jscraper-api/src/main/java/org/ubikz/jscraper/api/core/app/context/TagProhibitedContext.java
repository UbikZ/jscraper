package org.ubikz.jscraper.api.core.app.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.controller.filter.AbstractFilterBody;
import org.ubikz.jscraper.api.controller.filter.TagProhibitedFilterBody;
import org.ubikz.jscraper.api.controller.request.AbstractRequestBody;
import org.ubikz.jscraper.api.core.app.service.TagProhibitedService;
import org.ubikz.jscraper.api.core.app.service.filter.AbstractServiceFilter;
import org.ubikz.jscraper.api.core.app.service.filter.TagProhibitedServiceFilter;
import org.ubikz.jscraper.api.core.app.service.request.AbstractServiceRequest;
import org.ubikz.jscraper.api.core.app.service.request.TagProhibitedServiceRequest;

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
        return this.parseBaseRequest(data, request);
    }

    @Override
    protected AbstractServiceFilter parseFilter(AbstractFilterBody data, AbstractServiceFilter filter) throws Exception {
        return this.parseBaseFilter(data, filter);
    }
}