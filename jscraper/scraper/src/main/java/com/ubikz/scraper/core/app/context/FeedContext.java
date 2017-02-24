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
    final private int CODE_CREATE = 1;
    final private int CODE_UPDATE = 2;
    final private int CODE_GET_ONE = 3;

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
        }, HttpStatus.CREATED, CODE_CREATE);
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
        }, HttpStatus.OK, CODE_UPDATE);
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

            return this.feedService.getFeed(feedServiceFilter);
        }, HttpStatus.OK, CODE_GET_ONE);
    }
}