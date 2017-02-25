package com.ubikz.scraper.core.app.entity;

import com.ubikz.scraper.core.app.dal.FeedTypeDal;
import com.ubikz.scraper.core.app.dal.filter.AbstractDalFilter;
import com.ubikz.scraper.core.app.dal.filter.FeedTypeDalFilter;
import com.ubikz.scraper.core.app.dal.request.AbstractDalRequest;
import com.ubikz.scraper.core.app.dal.request.FeedTypeDalRequest;
import com.ubikz.scraper.core.app.dto.FeedTypeDto;
import com.ubikz.scraper.core.app.entity.filter.AbstractEntityFilter;
import com.ubikz.scraper.core.app.entity.filter.FeedTypeEntityFilter;
import com.ubikz.scraper.core.app.entity.helper.FeedTypeEntityHelper;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import com.ubikz.scraper.core.app.entity.request.FeedTypeEntityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeedTypeEntity extends AbstractEntity {
    protected FeedTypeDal feedTypeDal;

    @Autowired
    public FeedTypeEntity(FeedTypeDal feedTypeDal) {
        this.feedTypeDal = feedTypeDal;
    }

    /**
     * @param filter
     * @return
     */
    public List<FeedTypeDto> getAllFeedTypes(FeedTypeEntityFilter filter) {
        return FeedTypeEntityHelper.getDtoListFromDal(
                this.feedTypeDal.getAll(this.parseEntityToDalFilter(filter))
        );
    }

    /**
     * @param filter
     * @return
     */
    public FeedTypeDto getOneFeedType(FeedTypeEntityFilter filter) {
        return FeedTypeEntityHelper.getDtoFromDal(
                this.feedTypeDal.getOne(this.parseEntityToDalFilter(filter))
        );
    }

    /**
     * @param request
     * @return
     */
    public int createFeedType(FeedTypeEntityRequest request) {
        return this.feedTypeDal.create(this.parseEntityToDalRequest(request));
    }

    /**
     * @param request
     * @return
     */
    public int updateFeedType(FeedTypeEntityRequest request) {
        return this.feedTypeDal.edit(this.parseEntityToDalRequest(request));
    }

    /**
     * @param request
     * @return
     */
    @Override
    protected AbstractDalRequest parseEntityToDalRequest(AbstractEntityRequest request) {
        FeedTypeDalRequest feedTypeDalRequest = new FeedTypeDalRequest();
        FeedTypeEntityRequest feedTypeEntityRequest = (FeedTypeEntityRequest) request;

        feedTypeDalRequest = (FeedTypeDalRequest) this.parseBaseEntityToDalRequest(feedTypeEntityRequest, feedTypeDalRequest);
        feedTypeDalRequest.setUrlRegex(feedTypeEntityRequest.getUrlRegex());
        feedTypeDalRequest.setContentRegex(feedTypeEntityRequest.getContentRegex());

        return feedTypeDalRequest;
    }

    /**
     * @param filter
     * @return
     */
    @Override
    protected AbstractDalFilter parseEntityToDalFilter(AbstractEntityFilter filter) {
        FeedTypeDalFilter feedTypeDalFilter = new FeedTypeDalFilter();
        FeedTypeEntityFilter feedTypeEntityFilter = (FeedTypeEntityFilter) filter;

        feedTypeDalFilter = (FeedTypeDalFilter) this.parseBaseEntityToDalFilter(feedTypeEntityFilter, feedTypeDalFilter);

        return feedTypeDalFilter;
    }
}