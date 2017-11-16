package org.ubikz.jscraper.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.entity.impl.TagProhibitedEntity;
import org.ubikz.jscraper.api.entity.model.filter.AbstractEntityFilter;
import org.ubikz.jscraper.api.entity.model.filter.impl.TagProhibitedEntityFilter;
import org.ubikz.jscraper.api.entity.model.request.AbstractEntityRequest;
import org.ubikz.jscraper.api.entity.model.request.impl.TagProhibitedEntityRequest;
import org.ubikz.jscraper.api.service.BaseService;
import org.ubikz.jscraper.api.service.model.filter.BaseServiceFilter;
import org.ubikz.jscraper.api.service.model.filter.impl.TagProhibitedServiceFilter;
import org.ubikz.jscraper.api.service.model.request.BaseServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.TagProhibitedServiceRequest;

@Component
public class TagProhibitedService extends BaseService {
    @Autowired
    public TagProhibitedService(TagProhibitedEntity feedProhibitedEntity) {
        this.entity = feedProhibitedEntity;
    }

    @Override
    protected AbstractEntityRequest parseServiceToEntityRequest(BaseServiceRequest request) {
        TagProhibitedEntityRequest tagProhibitedEntityRequest = new TagProhibitedEntityRequest();
        TagProhibitedServiceRequest tagProhibitedServiceRequest = (TagProhibitedServiceRequest) request;

        this.parseBaseServiceToEntityRequest(tagProhibitedServiceRequest, tagProhibitedEntityRequest);

        return tagProhibitedEntityRequest;
    }

    @Override
    protected AbstractEntityFilter parseServiceToEntityFilter(BaseServiceFilter filter) {
        TagProhibitedEntityFilter tagProhibitedEntityFilter = new TagProhibitedEntityFilter();
        TagProhibitedServiceFilter tagProhibitedServiceFilter = (TagProhibitedServiceFilter) filter;

        this.parseBaseServiceToEntityFilter(tagProhibitedServiceFilter, tagProhibitedEntityFilter);

        return tagProhibitedEntityFilter;
    }
}