package com.ubikz.scraper.core.app.service;

import com.ubikz.scraper.core.app.dto.FeedDto;
import com.ubikz.scraper.core.app.entity.FeedEntity;
import com.ubikz.scraper.core.app.entity.filter.FeedEntityFilter;
import com.ubikz.scraper.core.app.entity.request.FeedEntityRequest;
import com.ubikz.scraper.core.app.service.filter.FeedServiceFilter;
import com.ubikz.scraper.core.app.service.request.FeedServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeedService extends AbstractService {
    private FeedEntity feedEntity;

    @Autowired
    public FeedService(FeedEntity feedEntity) {
        this.feedEntity = feedEntity;
    }

    /**
     * @param feedServiceFilter
     * @return
     * @throws Exception
     */
    public List<FeedDto> getFeed(FeedServiceFilter feedServiceFilter) throws Exception {
        FeedEntityFilter feedEntityFilter = new FeedEntityFilter();
        feedEntityFilter.setId(feedServiceFilter.getId());
        feedEntityFilter.setUrl(feedServiceFilter.getUrl());
        feedEntityFilter.setLabel(feedServiceFilter.getLabel());
        feedEntityFilter.setEnabled(feedServiceFilter.isEnabled());

        return this.feedEntity.getFeed(feedEntityFilter);
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public int createFeed(FeedServiceRequest request) throws Exception {
        return this.feedEntity.createFeed(this.parseServiceToEntityRequest(request));
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public int updateFeed(FeedServiceRequest request) throws Exception {
        return this.feedEntity.updateFeed(this.parseServiceToEntityRequest(request));
    }

    /**
     * @param request
     * @return
     */
    private FeedEntityRequest parseServiceToEntityRequest(FeedServiceRequest request) {
        FeedEntityRequest feedEntityRequest = new FeedEntityRequest();
        feedEntityRequest.setId(request.getId());
        feedEntityRequest.setUrl(request.getUrl());
        feedEntityRequest.setLabel(request.getLabel());
        feedEntityRequest.setEnabled(request.isEnabled());

        return feedEntityRequest;
    }
}