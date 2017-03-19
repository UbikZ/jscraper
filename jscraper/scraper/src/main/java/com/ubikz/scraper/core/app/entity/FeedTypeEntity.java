package com.ubikz.scraper.core.app.entity;

import com.ubikz.scraper.core.app.dal.FeedTypeDal;
import com.ubikz.scraper.core.app.dal.filter.AbstractDalFilter;
import com.ubikz.scraper.core.app.dal.filter.FeedTypeDalFilter;
import com.ubikz.scraper.core.app.dal.request.AbstractDalRequest;
import com.ubikz.scraper.core.app.dal.request.FeedTypeDalRequest;
import com.ubikz.scraper.core.app.dto.AbstractDto;
import com.ubikz.scraper.core.app.entity.filter.AbstractEntityFilter;
import com.ubikz.scraper.core.app.entity.filter.FeedTypeEntityFilter;
import com.ubikz.scraper.core.app.entity.helper.FeedTypeEntityHelper;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import com.ubikz.scraper.core.app.entity.request.FeedTypeEntityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class FeedTypeEntity extends AbstractEntity {
    @Autowired
    public FeedTypeEntity(FeedTypeDal feedTypeDal) {
        this.dal = feedTypeDal;
        this.helper = new FeedTypeEntityHelper();
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