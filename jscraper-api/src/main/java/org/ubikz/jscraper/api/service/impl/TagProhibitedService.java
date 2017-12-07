package org.ubikz.jscraper.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.dto.impl.TagProhibitedDto;
import org.ubikz.jscraper.api.entity.impl.TagProhibitedEntity;
import org.ubikz.jscraper.api.entity.model.filter.impl.TagProhibitedEntityFilter;
import org.ubikz.jscraper.api.entity.model.request.impl.TagProhibitedEntityRequest;
import org.ubikz.jscraper.api.service.BaseService;

@Component
public class TagProhibitedService extends BaseService<TagProhibitedEntity, TagProhibitedEntityRequest, TagProhibitedEntityFilter, TagProhibitedDto> {
    @Autowired
    public TagProhibitedService(TagProhibitedEntity entity) {
        this.entity = entity;
    }
}