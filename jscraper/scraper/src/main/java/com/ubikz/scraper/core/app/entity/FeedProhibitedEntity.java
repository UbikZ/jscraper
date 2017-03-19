package com.ubikz.scraper.core.app.entity;

import com.ubikz.scraper.core.app.dal.FeedProhibitedDal;
import com.ubikz.scraper.core.app.dal.filter.AbstractDalFilter;
import com.ubikz.scraper.core.app.dal.filter.FeedProhibitedDalFilter;
import com.ubikz.scraper.core.app.dal.request.AbstractDalRequest;
import com.ubikz.scraper.core.app.dal.request.FeedProhibitedDalRequest;
import com.ubikz.scraper.core.app.dto.AbstractDto;
import com.ubikz.scraper.core.app.entity.filter.AbstractEntityFilter;
import com.ubikz.scraper.core.app.entity.helper.FeedProhibitedEntityHelper;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeedProhibitedEntity extends AbstractEntity {
    @Autowired
    public FeedProhibitedEntity(FeedProhibitedDal feedProhibitedDal) {
        this.dal = feedProhibitedDal;
        this.helper = new FeedProhibitedEntityHelper();
    }

    @Override
    protected void computeLazyLoading(List<AbstractDto> dtoList) {

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