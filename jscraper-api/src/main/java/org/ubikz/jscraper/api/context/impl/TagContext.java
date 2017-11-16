package org.ubikz.jscraper.api.context.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.context.BaseContext;
import org.ubikz.jscraper.api.controller.model.filter.BaseFilterBody;
import org.ubikz.jscraper.api.controller.model.filter.impl.TagFilterBody;
import org.ubikz.jscraper.api.controller.model.request.BaseRequestBody;
import org.ubikz.jscraper.api.service.impl.TagService;
import org.ubikz.jscraper.api.service.model.filter.BaseServiceFilter;
import org.ubikz.jscraper.api.service.model.filter.impl.TagServiceFilter;
import org.ubikz.jscraper.api.service.model.request.BaseServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.TagServiceRequest;

@Component
public class TagContext extends BaseContext {
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
    protected BaseServiceRequest parseRequest(BaseRequestBody data, BaseServiceRequest request) {
        return parseBaseRequest(data, request);
    }

    @Override
    protected BaseServiceFilter parseFilter(BaseFilterBody data, BaseServiceFilter filter) throws Exception {
        return parseBaseFilter(data, filter);
    }
}