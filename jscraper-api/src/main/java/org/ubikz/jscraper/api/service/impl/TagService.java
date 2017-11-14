package org.ubikz.jscraper.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.entity.TagEntity;
import org.ubikz.jscraper.api.entity.model.filter.AbstractEntityFilter;
import org.ubikz.jscraper.api.entity.model.filter.impl.TagEntityFilter;
import org.ubikz.jscraper.api.entity.model.request.AbstractEntityRequest;
import org.ubikz.jscraper.api.entity.model.request.impl.TagEntityRequest;
import org.ubikz.jscraper.api.service.AbstractService;
import org.ubikz.jscraper.api.service.model.filter.AbstractServiceFilter;
import org.ubikz.jscraper.api.service.model.filter.impl.TagServiceFilter;
import org.ubikz.jscraper.api.service.model.request.AbstractServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.TagServiceRequest;

@Component
public class TagService extends AbstractService {
    @Autowired
    public TagService(TagEntity tagEntity) {
        this.entity = tagEntity;
    }

    @Override
    protected AbstractEntityRequest parseServiceToEntityRequest(AbstractServiceRequest request) {
        TagEntityRequest tagEntityRequest = new TagEntityRequest();
        TagServiceRequest tagServiceRequest = (TagServiceRequest) request;

        this.parseBaseServiceToEntityRequest(tagServiceRequest, tagEntityRequest);

        return tagEntityRequest;
    }

    @Override
    protected AbstractEntityFilter parseServiceToEntityFilter(AbstractServiceFilter filter) {
        TagEntityFilter tagEntityFilter = new TagEntityFilter();
        TagServiceFilter tagServiceFilter = (TagServiceFilter) filter;

        this.parseBaseServiceToEntityFilter(tagServiceFilter, tagEntityFilter);

        return tagEntityFilter;
    }
}