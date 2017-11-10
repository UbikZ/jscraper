package org.ubikz.jscraper.api.core.app.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.core.app.dal.FeedTypeDal;
import org.ubikz.jscraper.api.core.app.dal.filter.AbstractDalFilter;
import org.ubikz.jscraper.api.core.app.dal.filter.FeedTypeDalFilter;
import org.ubikz.jscraper.api.core.app.dal.request.AbstractDalRequest;
import org.ubikz.jscraper.api.core.app.dal.request.FeedTypeDalRequest;
import org.ubikz.jscraper.api.core.app.dto.AbstractDto;
import org.ubikz.jscraper.api.core.app.entity.filter.AbstractEntityFilter;
import org.ubikz.jscraper.api.core.app.entity.filter.FeedTypeEntityFilter;
import org.ubikz.jscraper.api.core.app.entity.helper.FeedTypeEntityHelper;
import org.ubikz.jscraper.api.core.app.entity.request.AbstractEntityRequest;
import org.ubikz.jscraper.api.core.app.entity.request.FeedTypeEntityRequest;

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