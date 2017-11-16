package org.ubikz.jscraper.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.entity.impl.FeedTypeEntity;
import org.ubikz.jscraper.api.entity.model.filter.AbstractEntityFilter;
import org.ubikz.jscraper.api.entity.model.filter.impl.FeedTypeEntityFilter;
import org.ubikz.jscraper.api.entity.model.request.AbstractEntityRequest;
import org.ubikz.jscraper.api.entity.model.request.impl.FeedTypeEntityRequest;
import org.ubikz.jscraper.api.service.BaseService;
import org.ubikz.jscraper.api.service.model.filter.BaseServiceFilter;
import org.ubikz.jscraper.api.service.model.filter.impl.FeedTypeServiceFilter;
import org.ubikz.jscraper.api.service.model.request.BaseServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.FeedTypeServiceRequest;

@Component
public class FeedTypeService extends BaseService {
    @Autowired
    public FeedTypeService(FeedTypeEntity feedTypeEntity) {
        this.entity = feedTypeEntity;
    }

    @Override
    protected AbstractEntityRequest parseServiceToEntityRequest(BaseServiceRequest request) {
        FeedTypeEntityRequest feedTypeEntityRequest = new FeedTypeEntityRequest();
        FeedTypeServiceRequest feedTypeServiceRequest = (FeedTypeServiceRequest) request;

        this.parseBaseServiceToEntityRequest(feedTypeServiceRequest, feedTypeEntityRequest);
        feedTypeEntityRequest.setUrlRegex(feedTypeServiceRequest.getUrlRegex());
        feedTypeEntityRequest.setContentRegex(feedTypeServiceRequest.getContentRegex());

        return feedTypeEntityRequest;
    }

    @Override
    protected AbstractEntityFilter parseServiceToEntityFilter(BaseServiceFilter filter) {
        FeedTypeEntityFilter feedTypeEntityFilter = new FeedTypeEntityFilter();
        FeedTypeServiceFilter feedTypeServiceFilter = (FeedTypeServiceFilter) filter;

        this.parseBaseServiceToEntityFilter(feedTypeServiceFilter, feedTypeEntityFilter);

        return feedTypeEntityFilter;
    }
}