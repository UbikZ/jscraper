package com.ubikz.scraper.core.app.entity;

import com.ubikz.scraper.core.app.dal.FeedDal;
import com.ubikz.scraper.core.app.dal.filter.AbstractDalFilter;
import com.ubikz.scraper.core.app.dal.filter.FeedDalFilter;
import com.ubikz.scraper.core.app.dal.request.AbstractDalRequest;
import com.ubikz.scraper.core.app.dal.request.FeedDalRequest;
import com.ubikz.scraper.core.app.dto.FeedArticleDto;
import com.ubikz.scraper.core.app.dto.FeedDto;
import com.ubikz.scraper.core.app.entity.filter.AbstractEntityFilter;
import com.ubikz.scraper.core.app.entity.filter.FeedEntityFilter;
import com.ubikz.scraper.core.app.entity.helper.FeedArticleEntityHelper;
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
     * @param filter
     * @return
     * @throws Exception
     */
    public List<FeedArticleDto> getRssFeedArticleList(FeedEntityFilter filter) throws Exception {
        return FeedArticleEntityHelper.getDtoListFromDal(
                this.feedDal.getRssFeedList((FeedDalFilter) this.parseEntityToDalFilter(filter))
        );
    }

    /**
     * @param filter
     * @return
     */
    public List<FeedDto> getAllFeeds(FeedEntityFilter filter) {
        return FeedEntityHelper.getDtoListFromDal(
                this.feedDal.getAll(this.parseEntityToDalFilter(filter))
        );
    }

    /**
     * @param filter
     * @return
     */
    public FeedDto getOneFeed(FeedEntityFilter filter) {
        return FeedEntityHelper.getDtoFromDal(
                this.feedDal.getOne(this.parseEntityToDalFilter(filter))
        );
    }

    /**
     * @param filter
     * @return
     */
    public int deleteFeed(FeedEntityFilter filter) {
        return this.feedDal.delete(this.parseEntityToDalFilter(filter));
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
        feedDalFilter.setProhibitedTagList(feedEntityFilter.getProhibitedTagList());
        feedDalFilter.setProhibitedFeedList(feedDalFilter.getProhibitedFeedList());

        return feedDalFilter;
    }
}