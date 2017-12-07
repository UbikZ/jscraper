package org.ubikz.jscraper.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.dto.impl.FeedProhibitedDto;
import org.ubikz.jscraper.api.entity.impl.FeedProhibitedEntity;
import org.ubikz.jscraper.api.entity.model.filter.BaseEntityFilter;
import org.ubikz.jscraper.api.entity.model.filter.impl.FeedProhibitedEntityFilter;
import org.ubikz.jscraper.api.entity.model.request.BaseEntityRequest;
import org.ubikz.jscraper.api.entity.model.request.impl.FeedProhibitedEntityRequest;
import org.ubikz.jscraper.api.service.BaseService;
import org.ubikz.jscraper.api.service.model.filter.BaseServiceFilter;
import org.ubikz.jscraper.api.service.model.filter.impl.FeedProhibitedServiceFilter;
import org.ubikz.jscraper.api.service.model.request.BaseServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.FeedProhibitedServiceRequest;

@Component
public class FeedProhibitedService extends BaseService<FeedProhibitedEntity, FeedProhibitedEntityRequest, FeedProhibitedEntityFilter, FeedProhibitedDto> {
    @Autowired
    public FeedProhibitedService(FeedProhibitedEntity entity) {
        this.entity = entity;
    }
}