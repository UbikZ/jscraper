package org.ubikz.jscraper.api.context.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.context.BaseContext;
import org.ubikz.jscraper.api.service.impl.TagProhibitedService;
import org.ubikz.jscraper.api.service.model.filter.impl.TagProhibitedServiceFilter;
import org.ubikz.jscraper.api.service.model.request.impl.TagProhibitedServiceRequest;

@Component
public class TagProhibitedContext extends BaseContext {
    @Autowired
    public TagProhibitedContext(TagProhibitedService tagProhibitedService) {
        this.service = tagProhibitedService;
        this.serviceRequest = new TagProhibitedServiceRequest();
        this.serviceFilter = new TagProhibitedServiceFilter();
    }
}
