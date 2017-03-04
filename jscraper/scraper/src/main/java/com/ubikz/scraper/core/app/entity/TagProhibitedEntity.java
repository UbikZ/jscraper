package com.ubikz.scraper.core.app.entity;

import com.ubikz.scraper.core.app.dal.TagProhibitedDal;
import com.ubikz.scraper.core.app.dal.filter.AbstractDalFilter;
import com.ubikz.scraper.core.app.dal.filter.TagProhibitedDalFilter;
import com.ubikz.scraper.core.app.dal.request.AbstractDalRequest;
import com.ubikz.scraper.core.app.dal.request.TagProhibitedDalRequest;
import com.ubikz.scraper.core.app.dto.TagProhibitedDto;
import com.ubikz.scraper.core.app.entity.filter.AbstractEntityFilter;
import com.ubikz.scraper.core.app.entity.filter.TagProhibitedEntityFilter;
import com.ubikz.scraper.core.app.entity.helper.TagProhibitedEntityHelper;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import com.ubikz.scraper.core.app.entity.request.TagProhibitedEntityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagProhibitedEntity extends AbstractEntity {
    protected TagProhibitedDal tagProhibitedDal;

    @Autowired
    public TagProhibitedEntity(TagProhibitedDal tagProhibitedDal) {
        this.tagProhibitedDal = tagProhibitedDal;
    }

    /**
     * @param filter
     * @return
     */
    public List<TagProhibitedDto> getAllTagsProhibited(TagProhibitedEntityFilter filter) {
        return TagProhibitedEntityHelper.getDtoListFromDal(
                this.tagProhibitedDal.getAll(this.parseEntityToDalFilter(filter))
        );
    }

    /**
     * @param filter
     * @return
     */
    public TagProhibitedDto getOneTagProhibited(TagProhibitedEntityFilter filter) {
        return TagProhibitedEntityHelper.getDtoFromDal(
                this.tagProhibitedDal.getOne(this.parseEntityToDalFilter(filter))
        );
    }

    /**
     * @param request
     * @return
     */
    public int createTagProhibited(TagProhibitedEntityRequest request) {
        return this.tagProhibitedDal.create(this.parseEntityToDalRequest(request));
    }

    /**
     * @param request
     * @return
     */
    public int updateTagProhibited(TagProhibitedEntityRequest request) {
        return this.tagProhibitedDal.edit(this.parseEntityToDalRequest(request));
    }

    /**
     * @param filter
     * @return
     */
    public int deleteTagProhibited(TagProhibitedEntityFilter filter) {
        return this.tagProhibitedDal.delete(this.parseEntityToDalFilter(filter));
    }

    /**
     * @param request
     * @return
     */
    @Override
    protected AbstractDalRequest parseEntityToDalRequest(AbstractEntityRequest request) {
        return this.parseBaseEntityToDalRequest(request, new TagProhibitedDalRequest());
    }

    /**
     * @param filter
     * @return
     */
    @Override
    protected AbstractDalFilter parseEntityToDalFilter(AbstractEntityFilter filter) {
        return this.parseBaseEntityToDalFilter(filter, new TagProhibitedDalFilter());
    }
}