package com.ubikz.scraper.core.app.context;

import com.ubikz.scraper.core.app.dto.FeedDto;
import com.ubikz.scraper.core.app.exception.MissingParameterException;
import com.ubikz.scraper.core.app.service.FeedService;
import com.ubikz.scraper.core.app.service.filter.FeedServiceFilter;
import com.ubikz.scraper.core.app.service.message.BaseMessage;
import com.ubikz.scraper.core.app.service.request.FeedServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class FeedContext extends AbstractContext {
    final private int FEED_CREATED = 1;
    final private int FEED_UPDATED = 2;
    final private int FEED_GET_ONE = 3;

    private FeedService feedService;

    @Autowired
    public FeedContext(FeedService feedService) {
        this.feedService = feedService;
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public BaseMessage createFeed(FeedDto request) throws Exception {
        return this.handle(() -> {
            FeedServiceRequest feedServiceRequest = new FeedServiceRequest();
            feedServiceRequest.setLabel(request.getLabel());
            feedServiceRequest.setUrl(request.getUrl());
            feedServiceRequest.setEnabled(request.isEnabled());

            return this.feedService.createFeed(feedServiceRequest);
        }, HttpStatus.CREATED, FEED_CREATED);
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public BaseMessage updateFeed(FeedDto request) throws Exception {
        return this.handle(() -> {
            if (request.getId() == null) {
                throw new MissingParameterException();
            }

            FeedServiceRequest feedServiceRequest = new FeedServiceRequest();
            feedServiceRequest.setId(request.getId());
            feedServiceRequest.setLabel(request.getLabel());
            feedServiceRequest.setUrl(request.getUrl());
            feedServiceRequest.setEnabled(request.isEnabled());

            return this.feedService.updateFeed(feedServiceRequest);
        }, HttpStatus.OK, FEED_UPDATED);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public BaseMessage getFeedById(int id) throws Exception {
        return this.handle(() -> {
            FeedServiceFilter feedServiceFilter = new FeedServiceFilter();
            feedServiceFilter.setId(id);

            return this.feedService.getOneFeed(feedServiceFilter);
        }, HttpStatus.OK, FEED_GET_ONE);
    }
}