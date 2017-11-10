package org.ubikz.jscraper.api.core.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.core.app.entity.TagProhibitedEntity;
import org.ubikz.jscraper.api.core.app.entity.filter.AbstractEntityFilter;
import org.ubikz.jscraper.api.core.app.entity.filter.TagProhibitedEntityFilter;
import org.ubikz.jscraper.api.core.app.entity.request.AbstractEntityRequest;
import org.ubikz.jscraper.api.core.app.entity.request.TagProhibitedEntityRequest;
import org.ubikz.jscraper.api.core.app.service.filter.AbstractServiceFilter;
import org.ubikz.jscraper.api.core.app.service.filter.TagProhibitedServiceFilter;
import org.ubikz.jscraper.api.core.app.service.request.AbstractServiceRequest;
import org.ubikz.jscraper.api.core.app.service.request.TagProhibitedServiceRequest;

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