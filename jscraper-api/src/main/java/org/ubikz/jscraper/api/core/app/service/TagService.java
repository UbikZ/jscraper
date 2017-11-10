package org.ubikz.jscraper.api.core.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.core.app.entity.TagEntity;
import org.ubikz.jscraper.api.core.app.entity.filter.AbstractEntityFilter;
import org.ubikz.jscraper.api.core.app.entity.filter.TagEntityFilter;
import org.ubikz.jscraper.api.core.app.entity.request.AbstractEntityRequest;
import org.ubikz.jscraper.api.core.app.entity.request.TagEntityRequest;
import org.ubikz.jscraper.api.core.app.service.filter.AbstractServiceFilter;
import org.ubikz.jscraper.api.core.app.service.filter.TagServiceFilter;
import org.ubikz.jscraper.api.core.app.service.request.AbstractServiceRequest;
import org.ubikz.jscraper.api.core.app.service.request.TagServiceRequest;

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