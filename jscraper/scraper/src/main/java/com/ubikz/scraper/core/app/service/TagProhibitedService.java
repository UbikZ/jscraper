package com.ubikz.scraper.core.app.service;

import com.ubikz.scraper.core.app.dto.TagProhibitedDto;
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

import java.util.List;

@Component
public class TagProhibitedService extends AbstractService {
    private TagProhibitedEntity feedProhibitedEntity;

    @Autowired
    public TagProhibitedService(TagProhibitedEntity feedProhibitedEntity) {
        this.feedProhibitedEntity = feedProhibitedEntity;
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public List<TagProhibitedDto> getAllTagsProhibited(TagProhibitedServiceFilter filter) throws Exception {
        return this.feedProhibitedEntity.getAllTagsProhibited(
                (TagProhibitedEntityFilter) this.parseServiceToEntityFilter(filter)
        );
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public TagProhibitedDto getOneTagProhibited(TagProhibitedServiceFilter filter) throws Exception {
        return this.feedProhibitedEntity.getOneTagProhibited(
                (TagProhibitedEntityFilter) this.parseServiceToEntityFilter(filter)
        );
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public int delete(TagProhibitedServiceFilter filter) throws Exception {
        return this.feedProhibitedEntity.deleteTagProhibited(
                (TagProhibitedEntityFilter) this.parseServiceToEntityFilter(filter)
        );
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public int createTagProhibited(TagProhibitedServiceRequest request) throws Exception {
        return this.feedProhibitedEntity.createTagProhibited((TagProhibitedEntityRequest) this.parseServiceToEntityRequest(request));
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public int updateTagProhibited(TagProhibitedServiceRequest request) throws Exception {
        return this.feedProhibitedEntity.updateTagProhibited((TagProhibitedEntityRequest) this.parseServiceToEntityRequest(request));
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