package org.ubikz.jscraper.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.entity.impl.TagEntity;
import org.ubikz.jscraper.api.entity.model.filter.AbstractEntityFilter;
import org.ubikz.jscraper.api.entity.model.filter.impl.TagEntityFilter;
import org.ubikz.jscraper.api.entity.model.request.AbstractEntityRequest;
import org.ubikz.jscraper.api.entity.model.request.impl.TagEntityRequest;
import org.ubikz.jscraper.api.service.BaseService;
import org.ubikz.jscraper.api.service.model.filter.BaseServiceFilter;
import org.ubikz.jscraper.api.service.model.filter.impl.TagServiceFilter;
import org.ubikz.jscraper.api.service.model.request.BaseServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.TagServiceRequest;

@Component
public class TagService extends BaseService {
    @Autowired
    public TagService(TagEntity tagEntity) {
        this.entity = tagEntity;
    }

    @Override
    protected AbstractEntityRequest parseServiceToEntityRequest(BaseServiceRequest request) {
        TagEntityRequest tagEntityRequest = new TagEntityRequest();
        TagServiceRequest tagServiceRequest = (TagServiceRequest) request;

        this.parseBaseServiceToEntityRequest(tagServiceRequest, tagEntityRequest);

        return tagEntityRequest;
    }

    @Override
    protected AbstractEntityFilter parseServiceToEntityFilter(BaseServiceFilter filter) {
        TagEntityFilter tagEntityFilter = new TagEntityFilter();
        TagServiceFilter tagServiceFilter = (TagServiceFilter) filter;

        this.parseBaseServiceToEntityFilter(tagServiceFilter, tagEntityFilter);

        return tagEntityFilter;
    }
}