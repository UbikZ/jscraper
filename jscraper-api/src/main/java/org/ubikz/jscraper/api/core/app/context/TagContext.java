package org.ubikz.jscraper.api.core.app.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.controller.filter.AbstractFilterBody;
import org.ubikz.jscraper.api.controller.filter.TagFilterBody;
import org.ubikz.jscraper.api.controller.request.AbstractRequestBody;
import org.ubikz.jscraper.api.core.app.service.TagService;
import org.ubikz.jscraper.api.core.app.service.filter.AbstractServiceFilter;
import org.ubikz.jscraper.api.core.app.service.filter.TagServiceFilter;
import org.ubikz.jscraper.api.core.app.service.request.AbstractServiceRequest;
import org.ubikz.jscraper.api.core.app.service.request.TagServiceRequest;

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
        return this.parseBaseRequest(data, request);
    }

    @Override
    protected AbstractServiceFilter parseFilter(AbstractFilterBody data, AbstractServiceFilter filter) throws Exception {
        return this.parseBaseFilter(data, filter);
    }
}