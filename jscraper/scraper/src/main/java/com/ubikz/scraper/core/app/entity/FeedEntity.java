package com.ubikz.scraper.core.app.entity;

import com.ubikz.scraper.core.app.dal.FeedDal;
import com.ubikz.scraper.core.app.dal.FeedTypeDal;
import com.ubikz.scraper.core.app.dal.filter.AbstractDalFilter;
import com.ubikz.scraper.core.app.dal.filter.FeedDalFilter;
import com.ubikz.scraper.core.app.dal.filter.FeedTypeDalFilter;
import com.ubikz.scraper.core.app.dal.request.AbstractDalRequest;
import com.ubikz.scraper.core.app.dal.request.FeedDalRequest;
import com.ubikz.scraper.core.app.dto.FeedArticleDto;
import com.ubikz.scraper.core.app.dto.FeedDto;
import com.ubikz.scraper.core.app.dto.FeedTypeDto;
import com.ubikz.scraper.core.app.entity.filter.AbstractEntityFilter;
import com.ubikz.scraper.core.app.entity.filter.FeedEntityFilter;
import com.ubikz.scraper.core.app.entity.helper.FeedArticleEntityHelper;
import com.ubikz.scraper.core.app.entity.helper.FeedEntityHelper;
import com.ubikz.scraper.core.app.entity.helper.FeedTypeEntityHelper;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import com.ubikz.scraper.core.app.entity.request.FeedEntityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FeedEntity extends AbstractEntity {
    protected FeedDal feedDal;
    protected FeedTypeDal feedTypeDal;

    @Autowired
    public FeedEntity(FeedDal feedDal, FeedTypeDal feedTypeDal) {
        this.feedDal = feedDal;
        this.feedTypeDal = feedTypeDal;
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
        List<FeedDto> result = FeedEntityHelper.getDtoListFromDal(
                this.feedDal.getAll(this.parseEntityToDalFilter(filter))
        );

        if (filter.isLazy()) {
            this.computeFeedTypeLazy(result);
        }

        return result;
    }

    /**
     * @param filter
     * @return
     */
    public FeedDto getOneFeed(FeedEntityFilter filter) {
        FeedDto result = FeedEntityHelper.getDtoFromDal(
                this.feedDal.getOne(this.parseEntityToDalFilter(filter))
        );

        if (filter.isLazy()) {
            this.computeFeedTypeLazy(Collections.singletonList(result));
        }

        return result;
    }

    /**
     * @param feedList
     */
    private void computeFeedTypeLazy(List<FeedDto> feedList) {
        FeedTypeDalFilter feedTypeDalFilter = new FeedTypeDalFilter();
        feedTypeDalFilter.setIdsList(feedList.stream().map(FeedDto::getId).collect(Collectors.toList()));

        Map<Integer, FeedTypeDto> feedTypeMap =
                FeedTypeEntityHelper.getDtoMapFromDal(this.feedTypeDal.getAll(feedTypeDalFilter));

        for (FeedDto feed : feedList) {
            if (feedTypeMap.containsKey(feed.getFeedTypeDto().getId())) {
                feed.setFeedTypeDto(feedTypeMap.get(feed.getFeedTypeDto().getId()));
            }
        }
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
        feedDalRequest.setFeedTypeId(feedEntityRequest.getFeedTypeId());

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
        feedDalFilter.setProhibitedFeedList(feedEntityFilter.getProhibitedFeedList());
        feedDalFilter.setUrlRegex(feedEntityFilter.getUrlRegex());

        return feedDalFilter;
    }
}