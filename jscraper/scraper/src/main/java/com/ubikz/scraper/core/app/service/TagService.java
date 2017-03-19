package com.ubikz.scraper.core.app.service;

import com.ubikz.scraper.core.app.entity.TagEntity;
import com.ubikz.scraper.core.app.entity.filter.AbstractEntityFilter;
import com.ubikz.scraper.core.app.entity.filter.TagEntityFilter;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import com.ubikz.scraper.core.app.entity.request.TagEntityRequest;
import com.ubikz.scraper.core.app.service.filter.AbstractServiceFilter;
import com.ubikz.scraper.core.app.service.filter.TagServiceFilter;
import com.ubikz.scraper.core.app.service.request.AbstractServiceRequest;
import com.ubikz.scraper.core.app.service.request.TagServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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