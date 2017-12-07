package org.ubikz.jscraper.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.dto.impl.TagDto;
import org.ubikz.jscraper.api.entity.impl.TagEntity;
import org.ubikz.jscraper.api.entity.model.filter.impl.TagEntityFilter;
import org.ubikz.jscraper.api.entity.model.request.impl.TagEntityRequest;
import org.ubikz.jscraper.api.service.BaseService;

@Component
public class TagService extends BaseService<TagEntity, TagEntityRequest, TagEntityFilter, TagDto> {
    @Autowired
    public TagService(TagEntity entity) {
        this.entity = entity;
    }
}