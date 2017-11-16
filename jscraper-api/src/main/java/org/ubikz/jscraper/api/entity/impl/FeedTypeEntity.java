package org.ubikz.jscraper.api.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.dal.impl.FeedTypeDal;
import org.ubikz.jscraper.api.dal.model.filter.AbstractDalFilter;
import org.ubikz.jscraper.api.dal.model.filter.impl.FeedTypeDalFilter;
import org.ubikz.jscraper.api.dal.model.request.AbstractDalRequest;
import org.ubikz.jscraper.api.dal.model.request.impl.FeedTypeDalRequest;
import org.ubikz.jscraper.api.dto.BaseDto;
import org.ubikz.jscraper.api.entity.BaseEntity;
import org.ubikz.jscraper.api.entity.model.filter.AbstractEntityFilter;
import org.ubikz.jscraper.api.entity.model.filter.impl.FeedTypeEntityFilter;
import org.ubikz.jscraper.api.entity.helper.impl.FeedTypeEntityHelper;
import org.ubikz.jscraper.api.entity.model.request.AbstractEntityRequest;
import org.ubikz.jscraper.api.entity.model.request.impl.FeedTypeEntityRequest;

import java.util.List;
import java.util.Map;

@Component
public class FeedTypeEntity extends BaseEntity {
    @Autowired
    public FeedTypeEntity(FeedTypeDal feedTypeDal) {
        this.dal = feedTypeDal;
        this.helper = new FeedTypeEntityHelper();
    }

    @Override
    protected void computeLoading(List<BaseDto> dtoList) {

    }

    @Override
    protected void computeLoading(Map<Object, BaseDto> dtoList) throws Exception {
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