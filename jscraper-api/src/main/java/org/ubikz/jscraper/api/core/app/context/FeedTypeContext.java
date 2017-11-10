package org.ubikz.jscraper.api.core.app.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.controller.filter.AbstractFilterBody;
import org.ubikz.jscraper.api.controller.filter.FeedTypeFilterBody;
import org.ubikz.jscraper.api.controller.request.AbstractRequestBody;
import org.ubikz.jscraper.api.controller.request.FeedTypeRequestBody;
import org.ubikz.jscraper.api.core.app.service.FeedTypeService;
import org.ubikz.jscraper.api.core.app.service.filter.AbstractServiceFilter;
import org.ubikz.jscraper.api.core.app.service.filter.FeedTypeServiceFilter;
import org.ubikz.jscraper.api.core.app.service.request.AbstractServiceRequest;
import org.ubikz.jscraper.api.core.app.service.request.FeedTypeServiceRequest;

@Component
public class FeedTypeContext extends AbstractContext {
    @Autowired
    public FeedTypeContext(FeedTypeService feedTypeService) {
        this.service = feedTypeService;
        this.serviceRequest = new FeedTypeServiceRequest();
        this.serviceFilter = new FeedTypeServiceFilter();
        this.filterBody = new FeedTypeFilterBody();

        CREATED = 20;
        UPDATED = 21;
        GET_ONE = 22;
        GET_ALL = 23;
        DELETE = 24;
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
    protected AbstractServiceFilter parseFilter(AbstractFilterBody data, AbstractServiceFilter filter) throws Exception {
        return this.parseBaseFilter(data, filter);
    }
}