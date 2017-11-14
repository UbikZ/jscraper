package org.ubikz.jscraper.api.context.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.context.AbstractContext;
import org.ubikz.jscraper.api.controller.model.filter.AbstractFilterBody;
import org.ubikz.jscraper.api.controller.model.filter.impl.TagFilterBody;
import org.ubikz.jscraper.api.controller.model.request.AbstractRequestBody;
import org.ubikz.jscraper.api.service.impl.TagService;
import org.ubikz.jscraper.api.service.model.filter.AbstractServiceFilter;
import org.ubikz.jscraper.api.service.model.filter.impl.TagServiceFilter;
import org.ubikz.jscraper.api.service.model.request.AbstractServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.TagServiceRequest;

@Component
public class TagContext extends AbstractContext {
    @Autowired
    public TagContext(TagService tagService) {
        this.service = tagService;
        this.serviceRequest = new TagServiceRequest();
        this.serviceFilter = new TagServiceFilter();
        this.filterBody = new TagFilterBody();

        CREATED = 30;
        UPDATED = 31;
        GET_ONE = 32;
        GET_ALL = 33;
        DELETE = 34;
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