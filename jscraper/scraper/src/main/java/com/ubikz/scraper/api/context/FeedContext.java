package com.ubikz.scraper.api.context;

import com.ubikz.scraper.api.dto.FeedDto;
import com.ubikz.scraper.api.service.FeedService;
import com.ubikz.scraper.api.service.filter.FeedServiceFilter;
import com.ubikz.scraper.api.service.message.BaseMessage;
import com.ubikz.scraper.api.service.request.FeedServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeedContext extends AbstractContext {
    private FeedService feedService;

    @Autowired
    public FeedContext(FeedService feedService) {
        this.feedService = feedService;
    }

    public BaseMessage createFeed(FeedDto request) throws Exception {
        return this.handle(() -> {
            FeedServiceRequest feedServiceRequest = new FeedServiceRequest();
            feedServiceRequest.setLabel(request.getLabel());
            feedServiceRequest.setUrl(request.getUrl());
            feedServiceRequest.setEnabled(request.isEnabled());

            return this.feedService.createFeed(feedServiceRequest);
        }, 201, 1);
    }

    public BaseMessage getFeedById(int id) throws Exception {
        return this.handle(() -> {
            FeedServiceFilter feedServiceFilter = new FeedServiceFilter();
            feedServiceFilter.setId(id);

            return this.feedService.getFeed(feedServiceFilter);
        }, 200, 3);
    }
}