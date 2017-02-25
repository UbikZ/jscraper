package com.ubikz.scraper.core.app.entity;

import com.ubikz.scraper.core.app.dal.TagDal;
import com.ubikz.scraper.core.app.dal.filter.AbstractDalFilter;
import com.ubikz.scraper.core.app.dal.filter.TagDalFilter;
import com.ubikz.scraper.core.app.dal.request.AbstractDalRequest;
import com.ubikz.scraper.core.app.dal.request.TagDalRequest;
import com.ubikz.scraper.core.app.dto.TagDto;
import com.ubikz.scraper.core.app.entity.filter.AbstractEntityFilter;
import com.ubikz.scraper.core.app.entity.filter.TagEntityFilter;
import com.ubikz.scraper.core.app.entity.helper.TagEntityHelper;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import com.ubikz.scraper.core.app.entity.request.TagEntityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagEntity extends AbstractEntity {
    protected TagDal tagDal;

    @Autowired
    public TagEntity(TagDal tagDal) {
        this.tagDal = tagDal;
    }

    /**
     * @param filter
     * @return
     */
    public List<TagDto> getAllTags(TagEntityFilter filter) {
        return TagEntityHelper.getDtoListFromDal(
                this.tagDal.getAll(this.parseEntityToDalFilter(filter))
        );
    }

    /**
     * @param filter
     * @return
     */
    public TagDto getOneTag(TagEntityFilter filter) {
        return TagEntityHelper.getDtoFromDal(
                this.tagDal.getOne(this.parseEntityToDalFilter(filter))
        );
    }

    /**
     * @param request
     * @return
     */
    public int createTag(TagEntityRequest request) {
        return this.tagDal.create(this.parseEntityToDalRequest(request));
    }

    /**
     * @param request
     * @return
     */
    public int updateTag(TagEntityRequest request) {
        return this.tagDal.edit(this.parseEntityToDalRequest(request));
    }

    /**
     * @param filter
     * @return
     */
    public int deleteTag(TagEntityFilter filter) {
        return this.tagDal.delete(this.parseEntityToDalFilter(filter));
    }

    /**
     * @param request
     * @return
     */
    @Override
    protected AbstractDalRequest parseEntityToDalRequest(AbstractEntityRequest request) {
        TagDalRequest tagDalRequest = new TagDalRequest();
        TagEntityRequest tagEntityRequest = (TagEntityRequest) request;

        tagDalRequest = (TagDalRequest) this.parseBaseEntityToDalRequest(tagEntityRequest, tagDalRequest);

        return tagDalRequest;
    }

    /**
     * @param filter
     * @return
     */
    @Override
    protected AbstractDalFilter parseEntityToDalFilter(AbstractEntityFilter filter) {
        TagDalFilter tagDalFilter = new TagDalFilter();
        TagEntityFilter tagEntityFilter = (TagEntityFilter) filter;

        tagDalFilter = (TagDalFilter) this.parseBaseEntityToDalFilter(tagEntityFilter, tagDalFilter);

        return tagDalFilter;
    }
}