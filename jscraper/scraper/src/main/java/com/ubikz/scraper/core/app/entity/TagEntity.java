package com.ubikz.scraper.core.app.entity;

import com.ubikz.scraper.core.app.dal.TagDal;
import com.ubikz.scraper.core.app.dal.filter.AbstractDalFilter;
import com.ubikz.scraper.core.app.dal.filter.TagDalFilter;
import com.ubikz.scraper.core.app.dal.request.AbstractDalRequest;
import com.ubikz.scraper.core.app.dal.request.TagDalRequest;
import com.ubikz.scraper.core.app.dto.AbstractDto;
import com.ubikz.scraper.core.app.entity.filter.AbstractEntityFilter;
import com.ubikz.scraper.core.app.entity.filter.TagEntityFilter;
import com.ubikz.scraper.core.app.entity.helper.TagEntityHelper;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import com.ubikz.scraper.core.app.entity.request.TagEntityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TagEntity extends AbstractEntity {
    @Autowired
    public TagEntity(TagDal tagDal) {
        this.dal = tagDal;
        this.helper = new TagEntityHelper();
    }

    @Override
    protected void computeLoading(List<AbstractDto> dtoList) {

    }

    @Override
    protected void computeLoading(Map<Object, AbstractDto> dtoList) throws Exception {
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