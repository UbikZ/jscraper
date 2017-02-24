package com.ubikz.scraper.core.app.entity;

import com.ubikz.scraper.core.app.dal.FeedDal;
import com.ubikz.scraper.core.app.dal.filter.FeedDalFilter;
import com.ubikz.scraper.core.app.dal.request.FeedDalRequest;
import com.ubikz.scraper.core.app.dto.FeedDto;
import com.ubikz.scraper.core.app.entity.filter.FeedEntityFilter;
import com.ubikz.scraper.core.app.entity.helper.FeedEntityHelper;
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
    public List<FeedDto> getFeed(FeedEntityFilter feedEntityFilter) {
        FeedDalFilter feedDalFilter = new FeedDalFilter();
        feedDalFilter.setId(feedEntityFilter.getId());
        feedDalFilter.setUrl(feedEntityFilter.getUrl());
        feedDalFilter.setEnabled(feedEntityFilter.isEnabled());

        return FeedEntityHelper.getDtoListFromDal(this.feedDal.getFeed(feedDalFilter));
    }

    /**
     * @param request
     * @return
     */
    public int createFeed(FeedEntityRequest request) {
        return this.feedDal.createFeed(this.parseEntityToDalRequest(request));
    }

    /**
     * @param request
     * @return
     */
    public int updateFeed(FeedEntityRequest request) {
        return this.feedDal.updateFeed(this.parseEntityToDalRequest(request));
    }

    private FeedDalRequest parseEntityToDalRequest(FeedEntityRequest request) {
        FeedDalRequest feedDalRequest = new FeedDalRequest();
        feedDalRequest.setId(request.getId());
        feedDalRequest.setUrl(request.getUrl());
        feedDalRequest.setLabel(request.getLabel());
        feedDalRequest.setEnabled(request.isEnabled());

        return feedDalRequest;
    }
}