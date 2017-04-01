package com.ubikz.scraper.core.app.context;

import com.ubikz.scraper.api.controller.filter.AbstractFilterBody;
import com.ubikz.scraper.api.controller.filter.TagFilterBody;
import com.ubikz.scraper.api.controller.request.AbstractRequestBody;
import com.ubikz.scraper.core.app.service.TagService;
import com.ubikz.scraper.core.app.service.filter.AbstractServiceFilter;
import com.ubikz.scraper.core.app.service.filter.TagServiceFilter;
import com.ubikz.scraper.core.app.service.request.AbstractServiceRequest;
import com.ubikz.scraper.core.app.service.request.TagServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TagContext extends AbstractContext {
    @Autowired
    public TagContext(TagService tagService) {
        this.service = tagService;
        this.serviceRequest = new TagServiceRequest();
        this.serviceFilter = new TagServiceFilter();
        this.filterBody = new TagFilterBody();

        this.CREATED = 30;
        this.UPDATED = 31;
        this.GET_ONE = 32;
        this.GET_ALL = 33;
        this.DELETE = 34;
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