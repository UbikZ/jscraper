package com.ubikz.scraper.core.app.service;

import com.ubikz.scraper.core.app.entity.FeedTypeEntity;
import com.ubikz.scraper.core.app.entity.filter.AbstractEntityFilter;
import com.ubikz.scraper.core.app.entity.filter.FeedTypeEntityFilter;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import com.ubikz.scraper.core.app.entity.request.FeedTypeEntityRequest;
import com.ubikz.scraper.core.app.service.filter.AbstractServiceFilter;
import com.ubikz.scraper.core.app.service.filter.FeedTypeServiceFilter;
import com.ubikz.scraper.core.app.service.request.AbstractServiceRequest;
import com.ubikz.scraper.core.app.service.request.FeedTypeServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeedTypeService extends AbstractService {
    @Autowired
    public FeedTypeService(FeedTypeEntity feedTypeEntity) {
        this.entity = feedTypeEntity;
    }

    @Override
    protected AbstractEntityRequest parseServiceToEntityRequest(AbstractServiceRequest request) {
        FeedTypeEntityRequest feedTypeEntityRequest = new FeedTypeEntityRequest();
        FeedTypeServiceRequest feedTypeServiceRequest = (FeedTypeServiceRequest) request;

        this.parseBaseServiceToEntityRequest(feedTypeServiceRequest, feedTypeEntityRequest);
        feedTypeEntityRequest.setUrlRegex(feedTypeServiceRequest.getUrlRegex());
        feedTypeEntityRequest.setContentRegex(feedTypeServiceRequest.getContentRegex());

        return feedTypeEntityRequest;
    }

    @Override
    protected AbstractEntityFilter parseServiceToEntityFilter(AbstractServiceFilter filter) {
        FeedTypeEntityFilter feedTypeEntityFilter = new FeedTypeEntityFilter();
        FeedTypeServiceFilter feedTypeServiceFilter = (FeedTypeServiceFilter) filter;

        this.parseBaseServiceToEntityFilter(feedTypeServiceFilter, feedTypeEntityFilter);

        return feedTypeEntityFilter;
    }
}