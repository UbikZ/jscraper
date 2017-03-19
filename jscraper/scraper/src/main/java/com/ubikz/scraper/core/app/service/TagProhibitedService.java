package com.ubikz.scraper.core.app.service;

import com.ubikz.scraper.core.app.entity.TagProhibitedEntity;
import com.ubikz.scraper.core.app.entity.filter.AbstractEntityFilter;
import com.ubikz.scraper.core.app.entity.filter.TagProhibitedEntityFilter;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import com.ubikz.scraper.core.app.entity.request.TagProhibitedEntityRequest;
import com.ubikz.scraper.core.app.service.filter.AbstractServiceFilter;
import com.ubikz.scraper.core.app.service.filter.TagProhibitedServiceFilter;
import com.ubikz.scraper.core.app.service.request.AbstractServiceRequest;
import com.ubikz.scraper.core.app.service.request.TagProhibitedServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TagProhibitedService extends AbstractService {
    @Autowired
    public TagProhibitedService(TagProhibitedEntity feedProhibitedEntity) {
        this.entity = feedProhibitedEntity;
    }

    @Override
    protected AbstractEntityRequest parseServiceToEntityRequest(AbstractServiceRequest request) {
        TagProhibitedEntityRequest tagProhibitedEntityRequest = new TagProhibitedEntityRequest();
        TagProhibitedServiceRequest tagProhibitedServiceRequest = (TagProhibitedServiceRequest) request;

        this.parseBaseServiceToEntityRequest(tagProhibitedServiceRequest, tagProhibitedEntityRequest);

        return tagProhibitedEntityRequest;
    }

    @Override
    protected AbstractEntityFilter parseServiceToEntityFilter(AbstractServiceFilter filter) {
        TagProhibitedEntityFilter tagProhibitedEntityFilter = new TagProhibitedEntityFilter();
        TagProhibitedServiceFilter tagProhibitedServiceFilter = (TagProhibitedServiceFilter) filter;

        this.parseBaseServiceToEntityFilter(tagProhibitedServiceFilter, tagProhibitedEntityFilter);

        return tagProhibitedEntityFilter;
    }
}