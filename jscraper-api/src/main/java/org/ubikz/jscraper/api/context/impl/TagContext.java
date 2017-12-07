package org.ubikz.jscraper.api.context.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.context.BaseContext;
import org.ubikz.jscraper.api.service.impl.TagService;
import org.ubikz.jscraper.api.service.model.filter.impl.TagServiceFilter;
import org.ubikz.jscraper.api.service.model.request.impl.TagServiceRequest;

@Component
public class TagContext extends BaseContext<TagService, TagServiceRequest, TagServiceFilter> {
    @Autowired
    public TagContext(TagService service) {
        this.service = service;
        this.serviceRequest = new TagServiceRequest();
        this.serviceFilter = new TagServiceFilter();
    }
}