package org.ubikz.jscraper.api.core.app.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.core.app.dal.FeedProhibitedDal;
import org.ubikz.jscraper.api.core.app.dal.filter.AbstractDalFilter;
import org.ubikz.jscraper.api.core.app.dal.filter.FeedProhibitedDalFilter;
import org.ubikz.jscraper.api.core.app.dal.request.AbstractDalRequest;
import org.ubikz.jscraper.api.core.app.dal.request.FeedProhibitedDalRequest;
import org.ubikz.jscraper.api.core.app.dto.AbstractDto;
import org.ubikz.jscraper.api.core.app.entity.filter.AbstractEntityFilter;
import org.ubikz.jscraper.api.core.app.entity.helper.FeedProhibitedEntityHelper;
import org.ubikz.jscraper.api.core.app.entity.request.AbstractEntityRequest;

import java.util.List;
import java.util.Map;

@Component
public class FeedProhibitedEntity extends AbstractEntity {
    @Autowired
    public FeedProhibitedEntity(FeedProhibitedDal feedProhibitedDal) {
        this.dal = feedProhibitedDal;
        this.helper = new FeedProhibitedEntityHelper();
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