package org.ubikz.jscraper.api.core.app.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.core.app.dal.TagProhibitedDal;
import org.ubikz.jscraper.api.core.app.dal.filter.AbstractDalFilter;
import org.ubikz.jscraper.api.core.app.dal.filter.TagProhibitedDalFilter;
import org.ubikz.jscraper.api.core.app.dal.request.AbstractDalRequest;
import org.ubikz.jscraper.api.core.app.dal.request.TagProhibitedDalRequest;
import org.ubikz.jscraper.api.core.app.dto.AbstractDto;
import org.ubikz.jscraper.api.core.app.entity.filter.AbstractEntityFilter;
import org.ubikz.jscraper.api.core.app.entity.helper.TagProhibitedEntityHelper;
import org.ubikz.jscraper.api.core.app.entity.request.AbstractEntityRequest;

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