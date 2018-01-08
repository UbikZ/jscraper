package org.ubikz.jscraper.api.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.dal.impl.FeedDal;
import org.ubikz.jscraper.api.dal.impl.FeedTypeDal;
import org.ubikz.jscraper.api.dal.model.filter.impl.FeedDalFilter;
import org.ubikz.jscraper.api.dal.model.filter.impl.FeedTypeDalFilter;
import org.ubikz.jscraper.api.dal.model.request.impl.FeedDalRequest;
import org.ubikz.jscraper.api.dto.impl.FeedArticleDto;
import org.ubikz.jscraper.api.dto.impl.FeedDto;
import org.ubikz.jscraper.api.dto.impl.FeedTypeDto;
import org.ubikz.jscraper.api.entity.BaseEntity;
import org.ubikz.jscraper.api.entity.helper.impl.FeedArticleEntityHelper;
import org.ubikz.jscraper.api.entity.helper.impl.FeedEntityHelper;
import org.ubikz.jscraper.api.entity.helper.impl.FeedTypeEntityHelper;
import org.ubikz.jscraper.api.entity.model.filter.BaseEntityFilter;
import org.ubikz.jscraper.api.entity.model.filter.impl.FeedEntityFilter;
import org.ubikz.jscraper.api.entity.model.request.BaseEntityRequest;
import org.ubikz.jscraper.api.entity.model.request.impl.FeedEntityRequest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FeedEntity extends BaseEntity<FeedDal, FeedDalRequest, FeedDalFilter, FeedEntityHelper, FeedDto> {
    private FeedTypeDal feedTypeDal;
    private FeedTypeEntityHelper feedTypeHelper;
    private FeedArticleEntityHelper feedArticleHelper;

    @Autowired
    public FeedEntity(FeedDal dal, FeedTypeDal feedTypeDal) {
        this.dal = dal;
        this.feedTypeDal = feedTypeDal;
        this.dalRequest = new FeedDalRequest();
        this.dalFilter = new FeedDalFilter();
        this.helper = new FeedEntityHelper();
        this.feedTypeHelper = new FeedTypeEntityHelper();
        this.feedArticleHelper = new FeedArticleEntityHelper();
    }

    public List<FeedArticleDto> getRssFeedArticleList(FeedEntityFilter filter) throws Exception {
        parseFilter(filter);

        return feedArticleHelper.getDtoListFromDal(dal.getRssFeedList(dalFilter));
    }

    @Override
    protected void computeLoading(List<FeedDto> feeds) {
        FeedTypeDalFilter feedTypeDalFilter = new FeedTypeDalFilter();
        feedTypeDalFilter.setIdList(feeds.stream().map(FeedDto::getId).collect(Collectors.toList()));

        Map<Integer, FeedTypeDto> feedTypeMap = feedTypeHelper
                .getDtoMapFromDal(feedTypeDal.getAll(feedTypeDalFilter), "id")
                .entrySet().stream()
                .collect(Collectors.toMap(p -> (int) p.getKey(), p -> (FeedTypeDto) p.getValue()));

        feeds.stream()
                .filter(f -> feedTypeMap.containsKey(f.getFeedTypeDto().getId()))
                .forEach(f -> f.setFeedTypeDto(feedTypeMap.get(f.getFeedTypeDto().getId())));
    }

    @Override
    protected void computeLoading(Map<Object, FeedDto> dtoList) {
    }

    @Override
    protected <T extends BaseEntityRequest> void parseRequest(T request) {
        super.parseRequest(request);
        FeedDalRequest feedDalRequest = new FeedDalRequest();
        FeedEntityRequest feedEntityRequest = (FeedEntityRequest) request;

        feedDalRequest.setUrl(feedEntityRequest.getUrl());
        feedDalRequest.setFeedTypeId(feedEntityRequest.getFeedTypeId());
    }

    @Override
    protected <T extends BaseEntityFilter> void parseFilter(T filter) {
        super.parseFilter(filter);
        FeedDalFilter feedDalFilter = new FeedDalFilter();
        FeedEntityFilter feedEntityFilter = (FeedEntityFilter) filter;

        feedDalFilter.setUrl(feedEntityFilter.getUrl());
        feedDalFilter.setProhibitedTagList(feedEntityFilter.getProhibitedTagList());
        feedDalFilter.setProhibitedFeedList(feedEntityFilter.getProhibitedFeedList());
        feedDalFilter.setUrlRegex(feedEntityFilter.getUrlRegex());
    }
}
