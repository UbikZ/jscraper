package com.ubikz.scraper.core.app.entity;

import com.ubikz.scraper.core.app.dal.FeedProhibitedDal;
import com.ubikz.scraper.core.app.dal.filter.AbstractDalFilter;
import com.ubikz.scraper.core.app.dal.filter.FeedProhibitedDalFilter;
import com.ubikz.scraper.core.app.dal.request.AbstractDalRequest;
import com.ubikz.scraper.core.app.dal.request.FeedProhibitedDalRequest;
import com.ubikz.scraper.core.app.dto.FeedProhibitedDto;
import com.ubikz.scraper.core.app.entity.filter.AbstractEntityFilter;
import com.ubikz.scraper.core.app.entity.filter.FeedProhibitedEntityFilter;
import com.ubikz.scraper.core.app.entity.helper.FeedProhibitedEntityHelper;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import com.ubikz.scraper.core.app.entity.request.FeedProhibitedEntityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeedProhibitedEntity extends AbstractEntity {
    protected FeedProhibitedDal feedProhibitedDal;

    @Autowired
    public FeedProhibitedEntity(FeedProhibitedDal feedProhibitedDal) {
        this.feedProhibitedDal = feedProhibitedDal;
    }

    /**
     * @param filter
     * @return
     */
    public List<FeedProhibitedDto> getAllFeedsProhibited(FeedProhibitedEntityFilter filter) {
        return FeedProhibitedEntityHelper.getDtoListFromDal(
                this.feedProhibitedDal.getAll(this.parseEntityToDalFilter(filter))
        );
    }

    /**
     * @param filter
     * @return
     */
    public FeedProhibitedDto getOneFeedProhibited(FeedProhibitedEntityFilter filter) {
        return FeedProhibitedEntityHelper.getDtoFromDal(
                this.feedProhibitedDal.getOne(this.parseEntityToDalFilter(filter))
        );
    }

    /**
     * @param request
     * @return
     */
    public int createFeedProhibited(FeedProhibitedEntityRequest request) {
        return this.feedProhibitedDal.create(this.parseEntityToDalRequest(request));
    }

    /**
     * @param request
     * @return
     */
    public int updateFeedProhibited(FeedProhibitedEntityRequest request) {
        return this.feedProhibitedDal.edit(this.parseEntityToDalRequest(request));
    }

    /**
     * @param filter
     * @return
     */
    public int deleteFeedProhibited(FeedProhibitedEntityFilter filter) {
        return this.feedProhibitedDal.delete(this.parseEntityToDalFilter(filter));
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