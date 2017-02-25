package com.ubikz.scraper.core.app.context;

import com.ubikz.scraper.core.app.dto.AbstractDto;
import com.ubikz.scraper.core.app.dto.FeedDto;
import com.ubikz.scraper.core.app.exception.MissingParameterException;
import com.ubikz.scraper.core.app.service.FeedService;
import com.ubikz.scraper.core.app.service.filter.AbstractServiceFilter;
import com.ubikz.scraper.core.app.service.filter.FeedServiceFilter;
import com.ubikz.scraper.core.app.service.message.BaseMessage;
import com.ubikz.scraper.core.app.service.request.AbstractServiceRequest;
import com.ubikz.scraper.core.app.service.request.FeedServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class FeedContext extends AbstractContext {
    final private int FEED_CREATED = 10;
    final private int FEED_UPDATED = 11;
    final private int FEED_GET_ONE = 12;
    final private int FEED_GET_ALL = 12;
    final private int FEED_DELETE = 13;

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
            FeedServiceRequest serviceRequest = new FeedServiceRequest();

            return this.feedService.createFeed(
                    (FeedServiceRequest) this.parseRequest(request, serviceRequest)
            );
        }, HttpStatus.CREATED, FEED_CREATED);
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public BaseMessage updateFeed(FeedDto request) throws Exception {
        return this.handle(() -> {
            FeedServiceRequest serviceRequest = new FeedServiceRequest();

            if (request.getId() == null) {
                throw new MissingParameterException();
            }

            return this.feedService.updateFeed(
                    (FeedServiceRequest) this.parseRequest(request, serviceRequest)
            );
        }, HttpStatus.OK, FEED_UPDATED);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public BaseMessage getFeedById(int id) throws Exception {
        FeedDto filter = new FeedDto();
        filter.setId(id);

        return this.handle(() -> {
            FeedServiceFilter serviceFilter = new FeedServiceFilter();

            return this.feedService.getOneFeed((FeedServiceFilter) this.parseFilter(filter, serviceFilter));
        }, HttpStatus.OK, FEED_GET_ONE);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public BaseMessage deleteFeedById(int id) throws Exception {
        FeedDto filter = new FeedDto();
        filter.setId(id);

        return this.handle(() -> {
            FeedServiceFilter serviceFilter = new FeedServiceFilter();

            return this.feedService.delete((FeedServiceFilter) this.parseFilter(filter, serviceFilter));
        }, HttpStatus.OK, FEED_DELETE);
    }

    /**
     * @param enabled
     * @return
     * @throws Exception
     */
    public BaseMessage getAllFeeds(Boolean enabled) throws Exception {
        FeedDto filter = new FeedDto();
        filter.setEnabled(enabled);

        return this.handle(() -> {
            FeedServiceFilter serviceFilter = new FeedServiceFilter();

            return this.feedService.getAllFeeds((FeedServiceFilter) this.parseFilter(filter, serviceFilter));
        }, HttpStatus.OK, FEED_GET_ALL);
    }

    @Override
    protected AbstractServiceRequest parseRequest(AbstractDto data, AbstractServiceRequest request) {
        FeedDto feedDto = (FeedDto) data;
        FeedServiceRequest serviceRequest = (FeedServiceRequest) this.parseBaseRequest(feedDto, request);
        serviceRequest.setUrl(feedDto.getUrl());

        return serviceRequest;
    }

    @Override
    protected AbstractServiceFilter parseFilter(AbstractDto data, AbstractServiceFilter filter) {
        FeedDto feedDto = (FeedDto) data;
        FeedServiceFilter serviceFilter = (FeedServiceFilter) this.parseBaseFilter(feedDto, filter);
        serviceFilter.setUrl(feedDto.getUrl());

        return serviceFilter;
    }
}