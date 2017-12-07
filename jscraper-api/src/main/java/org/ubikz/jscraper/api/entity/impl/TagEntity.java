package org.ubikz.jscraper.api.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.dal.impl.TagDal;
import org.ubikz.jscraper.api.dal.model.filter.impl.TagDalFilter;
import org.ubikz.jscraper.api.dal.model.request.impl.TagDalRequest;
import org.ubikz.jscraper.api.dto.impl.TagDto;
import org.ubikz.jscraper.api.entity.BaseEntity;
import org.ubikz.jscraper.api.entity.helper.impl.TagEntityHelper;
import org.ubikz.jscraper.api.entity.model.filter.BaseEntityFilter;
import org.ubikz.jscraper.api.entity.model.filter.impl.TagEntityFilter;

import java.util.List;
import java.util.Map;

@Component
public class TagEntity extends BaseEntity<TagDal, TagDalRequest, TagDalFilter, TagEntityHelper, TagDto> {
    @Autowired
    public TagEntity(TagDal tagDal) {
        this.dal = tagDal;
        this.helper = new TagEntityHelper();
        this.dalRequest = new TagDalRequest();
        this.dalFilter = new TagDalFilter();
    }

    @Override
    protected <T extends BaseEntityFilter> void parseFilter(T filter) {
        super.parseFilter(filter);
        TagEntityFilter tagEntityFilter = (TagEntityFilter) filter;

        dalFilter.setNameList(tagEntityFilter.getNameList());
    }

    @Override
    protected void computeLoading(List<TagDto> dtoList) {
    }

    @Override
    protected void computeLoading(Map<Object, TagDto> dtoList) {
    }
}
