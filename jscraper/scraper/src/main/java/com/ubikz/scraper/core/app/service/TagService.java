package com.ubikz.scraper.core.app.service;

import com.ubikz.scraper.core.app.dto.TagDto;
import com.ubikz.scraper.core.app.entity.TagEntity;
import com.ubikz.scraper.core.app.entity.filter.AbstractEntityFilter;
import com.ubikz.scraper.core.app.entity.filter.TagEntityFilter;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import com.ubikz.scraper.core.app.entity.request.TagEntityRequest;
import com.ubikz.scraper.core.app.service.filter.AbstractServiceFilter;
import com.ubikz.scraper.core.app.service.filter.TagServiceFilter;
import com.ubikz.scraper.core.app.service.request.AbstractServiceRequest;
import com.ubikz.scraper.core.app.service.request.FeedServiceRequest;
import com.ubikz.scraper.core.app.service.request.TagServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagService extends AbstractService {
    private TagEntity tagEntity;

    @Autowired
    public TagService(TagEntity tagEntity) {
        this.tagEntity = tagEntity;
    }


    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public List<TagDto> getAllTags(TagServiceFilter filter) throws Exception {
        return this.tagEntity.getAllTags(
                (TagEntityFilter) this.parseServiceToEntityFilter(filter)
        );
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public TagDto getOneTag(TagServiceFilter filter) throws Exception {
        return this.tagEntity.getOneTag(
                (TagEntityFilter) this.parseServiceToEntityFilter(filter)
        );
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public int delete(TagServiceFilter filter) throws Exception {
        return this.tagEntity.deleteTag(
                (TagEntityFilter) this.parseServiceToEntityFilter(filter)
        );
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public int createTag(TagServiceRequest request) throws Exception {
        return this.tagEntity.createTag(
                (TagEntityRequest) this.parseServiceToEntityRequest(request)
        );
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public int updateTag(FeedServiceRequest request) throws Exception {
        return this.tagEntity.updateTag(
                (TagEntityRequest) this.parseServiceToEntityRequest(request)
        );
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