package org.ubikz.jscraper.api.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.dal.impl.TagProhibitedDal;
import org.ubikz.jscraper.api.dal.model.filter.impl.TagProhibitedDalFilter;
import org.ubikz.jscraper.api.dal.model.request.impl.TagProhibitedDalRequest;
import org.ubikz.jscraper.api.dto.impl.TagProhibitedDto;
import org.ubikz.jscraper.api.entity.BaseEntity;
import org.ubikz.jscraper.api.entity.helper.impl.TagProhibitedEntityHelper;

import java.util.List;
import java.util.Map;

@Component
public class TagProhibitedEntity extends BaseEntity<TagProhibitedDal, TagProhibitedDalRequest, TagProhibitedDalFilter, TagProhibitedEntityHelper, TagProhibitedDto> {
    @Autowired
    public TagProhibitedEntity(TagProhibitedDal tagProhibitedDal) {
        this.dal = tagProhibitedDal;
        this.helper = new TagProhibitedEntityHelper();
        this.dalRequest = new TagProhibitedDalRequest();
        this.dalFilter = new TagProhibitedDalFilter();
    }

    @Override
    protected void computeLoading(List<TagProhibitedDto> dtoList) {
    }

    @Override
    protected void computeLoading(Map<Object, TagProhibitedDto> dtoList) {
    }
}
