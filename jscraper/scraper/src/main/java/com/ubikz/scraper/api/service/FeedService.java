package com.ubikz.scraper.api.service;

import com.ubikz.scraper.api.dto.FeedDto;
import com.ubikz.scraper.api.entity.FeedEntity;
import com.ubikz.scraper.api.entity.filter.FeedEntityFilter;
import com.ubikz.scraper.api.entity.request.FeedEntityRequest;
import com.ubikz.scraper.api.service.filter.FeedServiceFilter;
import com.ubikz.scraper.api.service.request.FeedServiceRequest;
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

    public List<FeedDto> getFeed(FeedServiceFilter feedServiceFilter) throws Exception {
        FeedEntityFilter feedEntityFilter = new FeedEntityFilter();
        feedEntityFilter.setId(feedServiceFilter.getId());
        feedEntityFilter.setUrl(feedServiceFilter.getUrl());
        feedEntityFilter.setLabel(feedServiceFilter.getLabel());
        feedEntityFilter.setEnabled(feedServiceFilter.isEnabled());

        return this.feedEntity.getFeed(feedEntityFilter);
    }

    public int createFeed(FeedServiceRequest feedServiceRequest) throws Exception {
        FeedEntityRequest feedEntityRequest = new FeedEntityRequest();
        feedEntityRequest.setId(feedServiceRequest.getId());
        feedEntityRequest.setUrl(feedServiceRequest.getUrl());
        feedEntityRequest.setLabel(feedServiceRequest.getLabel());
        feedEntityRequest.setEnabled(feedServiceRequest.isEnabled());

        return this.feedEntity.createFeed(feedEntityRequest);
    }
}