package org.ubikz.jscraper.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.dto.impl.FeedDto;
import org.ubikz.jscraper.api.entity.impl.FeedEntity;
import org.ubikz.jscraper.api.entity.model.filter.impl.FeedEntityFilter;
import org.ubikz.jscraper.api.entity.model.request.impl.FeedEntityRequest;
import org.ubikz.jscraper.api.service.BaseService;
import org.ubikz.jscraper.api.service.model.filter.BaseServiceFilter;
import org.ubikz.jscraper.api.service.model.filter.impl.FeedServiceFilter;
import org.ubikz.jscraper.api.service.model.request.BaseServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.FeedServiceRequest;

@Component
public class FeedService extends BaseService<FeedEntity, FeedEntityRequest, FeedEntityFilter, FeedDto> {
    @Autowired
    public FeedService(FeedEntity entity) {
        this.entity = entity;
    }

    @Override
    protected <T extends BaseServiceRequest> void parseRequest(T data) {
        super.parseRequest(data);
        FeedServiceRequest serviceRequest = (FeedServiceRequest) data;

        entityRequest.setUrl(serviceRequest.getUrl());
        entityRequest.setFeedTypeId(serviceRequest.getFeedTypeId());
    }

    @Override
    protected <T extends BaseServiceFilter> void parseFilter(T data) {
        super.parseFilter(data);
        FeedServiceFilter feedServiceFilter = (FeedServiceFilter) data;

        entityFilter.setUrl(feedServiceFilter.getUrl());
    }
}
