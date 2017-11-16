package org.ubikz.jscraper.api.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.dal.impl.FeedProhibitedDal;
import org.ubikz.jscraper.api.dal.model.filter.AbstractDalFilter;
import org.ubikz.jscraper.api.dal.model.filter.impl.FeedProhibitedDalFilter;
import org.ubikz.jscraper.api.dal.model.request.AbstractDalRequest;
import org.ubikz.jscraper.api.dal.model.request.impl.FeedProhibitedDalRequest;
import org.ubikz.jscraper.api.dto.BaseDto;
import org.ubikz.jscraper.api.entity.BaseEntity;
import org.ubikz.jscraper.api.entity.model.filter.AbstractEntityFilter;
import org.ubikz.jscraper.api.entity.helper.impl.FeedProhibitedEntityHelper;
import org.ubikz.jscraper.api.entity.model.request.AbstractEntityRequest;

import java.util.List;
import java.util.Map;

@Component
public class FeedProhibitedEntity extends BaseEntity {
    @Autowired
    public FeedProhibitedEntity(FeedProhibitedDal feedProhibitedDal) {
        this.dal = feedProhibitedDal;
        this.helper = new FeedProhibitedEntityHelper();
    }

    @Override
    protected void computeLoading(List<BaseDto> dtoList) {

    }

    @Override
    protected void computeLoading(Map<Object, BaseDto> dtoList) throws Exception {
    }

    /**
     * @param request
     * @return
     */
    @Override
    protected AbstractDalRequest parseEntityToDalRequest(AbstractEntityRequest request) {
        return this.parseBaseEntityToDalRequest(request, new FeedProhibitedDalRequest());
    }

    /**
     * @param filter
     * @return
     */
    @Override
    protected AbstractDalFilter parseEntityToDalFilter(AbstractEntityFilter filter) {
        return this.parseBaseEntityToDalFilter(filter, new FeedProhibitedDalFilter());
    }
}