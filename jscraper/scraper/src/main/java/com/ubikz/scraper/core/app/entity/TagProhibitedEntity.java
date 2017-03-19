package com.ubikz.scraper.core.app.entity;

import com.ubikz.scraper.core.app.dal.TagProhibitedDal;
import com.ubikz.scraper.core.app.dal.filter.AbstractDalFilter;
import com.ubikz.scraper.core.app.dal.filter.TagProhibitedDalFilter;
import com.ubikz.scraper.core.app.dal.request.AbstractDalRequest;
import com.ubikz.scraper.core.app.dal.request.TagProhibitedDalRequest;
import com.ubikz.scraper.core.app.dto.AbstractDto;
import com.ubikz.scraper.core.app.entity.filter.AbstractEntityFilter;
import com.ubikz.scraper.core.app.entity.helper.TagProhibitedEntityHelper;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TagProhibitedEntity extends AbstractEntity {
    @Autowired
    public TagProhibitedEntity(TagProhibitedDal tagProhibitedDal) {
        this.dal = tagProhibitedDal;
        this.helper = new TagProhibitedEntityHelper();
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