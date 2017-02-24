package com.ubikz.scraper.core.app.entity;

import com.ubikz.scraper.core.app.dal.FeedDal;
import com.ubikz.scraper.core.app.dal.filter.AbstractDalFilter;
import com.ubikz.scraper.core.app.dal.filter.FeedDalFilter;
import com.ubikz.scraper.core.app.dal.request.AbstractDalRequest;
import com.ubikz.scraper.core.app.dal.request.FeedDalRequest;
import com.ubikz.scraper.core.app.dto.FeedDto;
import com.ubikz.scraper.core.app.entity.filter.AbstractEntityFilter;
import com.ubikz.scraper.core.app.entity.filter.FeedEntityFilter;
import com.ubikz.scraper.core.app.entity.helper.FeedEntityHelper;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import com.ubikz.scraper.core.app.entity.request.FeedEntityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeedEntity extends AbstractEntity {
    protected FeedDal feedDal;

    @Autowired
    public FeedEntity(FeedDal feedDal) {
        this.feedDal = feedDal;
    }

    /**
     * @param feedEntityFilter
     * @return
     */
    private FeedDalFilter parseFilter(FeedEntityFilter feedEntityFilter) {
        FeedDalFilter feedDalFilter = new FeedDalFilter();
        feedDalFilter.setId(feedEntityFilter.getId());
        feedDalFilter.setUrl(feedEntityFilter.getUrl());
        feedDalFilter.setEnabled(feedEntityFilter.isEnabled());

        return feedDalFilter;
    }

    /**
     * @param feedEntityFilter
     * @return
     */
    public List<FeedDto> getFeed(FeedEntityFilter feedEntityFilter) {
        return FeedEntityHelper.getDtoListFromDal(this.feedDal.get(this.parseFilter(feedEntityFilter)));
    }

    /**
     * @param feedEntityFilter
     * @return
     */
    public FeedDto getOneFeed(FeedEntityFilter feedEntityFilter) {
        return FeedEntityHelper.getDtoFromDal(this.feedDal.getOne(this.parseFilter(feedEntityFilter)));
    }

    /**
     * @param request
     * @return
     */
    public int createFeed(FeedEntityRequest request) {
        return this.feedDal.create(this.parseEntityToDalRequest(request));
    }

    /**
     * @param request
     * @return
     */
    public int updateFeed(FeedEntityRequest request) {
        return this.feedDal.edit(this.parseEntityToDalRequest(request));
    }

    /**
     * @param request
     * @return
     */
    @Override
    protected AbstractDalRequest parseEntityToDalRequest(AbstractEntityRequest request) {
        FeedDalRequest feedDalRequest = new FeedDalRequest();
        FeedEntityRequest feedEntityRequest = (FeedEntityRequest) request;

        feedDalRequest = (FeedDalRequest) this.parseBaseEntityToDalRequest(feedEntityRequest, feedDalRequest);
        feedDalRequest.setUrl(feedEntityRequest.getUrl());

        return feedDalRequest;
    }

    /**
     * @param filter
     * @return
     */
    @Override
    protected AbstractDalFilter parseEntityToDalFilter(AbstractEntityFilter filter) {
        FeedDalFilter feedDalFilter = new FeedDalFilter();
        FeedEntityFilter feedEntityFilter = (FeedEntityFilter) filter;

        feedDalFilter = (FeedDalFilter) this.parseBaseEntityToDalFilter(feedEntityFilter, feedDalFilter);
        feedDalFilter.setUrl(feedEntityFilter.getUrl());

        return feedDalFilter;
    }
}