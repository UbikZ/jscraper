package org.ubikz.jscraper.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.dto.impl.FeedTypeDto;
import org.ubikz.jscraper.api.entity.impl.FeedTypeEntity;
import org.ubikz.jscraper.api.entity.model.filter.impl.FeedTypeEntityFilter;
import org.ubikz.jscraper.api.entity.model.request.impl.FeedTypeEntityRequest;
import org.ubikz.jscraper.api.service.BaseService;
import org.ubikz.jscraper.api.service.model.request.BaseServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.FeedTypeServiceRequest;

@Component
public class FeedTypeService extends BaseService<FeedTypeEntity, FeedTypeEntityRequest, FeedTypeEntityFilter, FeedTypeDto> {
    @Autowired
    public FeedTypeService(FeedTypeEntity feedTypeEntity) {
        this.entity = feedTypeEntity;
    }

    @Override
    protected <T extends BaseServiceRequest> void parseRequest(T data) {
        super.parseRequest(data);
        FeedTypeServiceRequest serviceRequest = (FeedTypeServiceRequest) data;

        entityRequest.setUrlRegex(serviceRequest.getUrlRegex());
        entityRequest.setContentRegex(serviceRequest.getContentRegex());
    }
}
