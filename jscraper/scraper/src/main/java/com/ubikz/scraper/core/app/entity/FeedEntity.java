package com.ubikz.scraper.core.app.entity;

import com.ubikz.scraper.core.app.dal.FeedDal;
import com.ubikz.scraper.core.app.dal.FeedTypeDal;
import com.ubikz.scraper.core.app.dal.filter.AbstractDalFilter;
import com.ubikz.scraper.core.app.dal.filter.FeedDalFilter;
import com.ubikz.scraper.core.app.dal.filter.FeedTypeDalFilter;
import com.ubikz.scraper.core.app.dal.request.AbstractDalRequest;
import com.ubikz.scraper.core.app.dal.request.FeedDalRequest;
import com.ubikz.scraper.core.app.dto.AbstractDto;
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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FeedEntity extends AbstractEntity {
    private FeedTypeDal feedTypeDal;
    private FeedTypeEntityHelper feedTypeHelper;
    private FeedArticleEntityHelper feedArticleHelper;

    @Autowired
    public FeedEntity(FeedDal feedDal, FeedTypeDal feedTypeDal) {
        this.dal = feedDal;
        this.helper = new FeedEntityHelper();
        this.feedTypeHelper = new FeedTypeEntityHelper();
        this.feedArticleHelper = new FeedArticleEntityHelper();
        this.feedTypeDal = feedTypeDal;
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public List<FeedArticleDto> getRssFeedArticleList(FeedEntityFilter filter) throws Exception {
        List<AbstractDto> abstractList = this.feedArticleHelper.getDtoListFromDal(
                ((FeedDal) this.dal).getRssFeedList((FeedDalFilter) this.parseEntityToDalFilter(filter))
        );

        return abstractList.stream().map(FeedArticleDto.class::cast).collect(Collectors.toList());
    }

    /**
     * @param feeds
     */
    @Override
    protected void computeLazyLoading(List<AbstractDto> feeds) {
        List<FeedDto> feedList = feeds.stream().map(FeedDto.class::cast).collect(Collectors.toList());

        FeedTypeDalFilter feedTypeDalFilter = new FeedTypeDalFilter();
        feedTypeDalFilter.setIdsList(feedList.stream().map(FeedDto::getId).collect(Collectors.toList()));

        Map<Integer, FeedTypeDto> feedTypeMap = this.feedTypeHelper
                .getDtoMapFromDal(this.feedTypeDal.getAll(feedTypeDalFilter))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, p -> (FeedTypeDto) p.getValue()));

        for (FeedDto feed : feedList) {
            if (feedTypeMap.containsKey(feed.getFeedTypeDto().getId())) {
                feed.setFeedTypeDto(feedTypeMap.get(feed.getFeedTypeDto().getId()));
            }
        }
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