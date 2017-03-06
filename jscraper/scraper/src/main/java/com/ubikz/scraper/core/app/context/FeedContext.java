package com.ubikz.scraper.core.app.context;

import com.ubikz.scraper.api.controller.filter.AbstractFilterBody;
import com.ubikz.scraper.api.controller.filter.FeedFilterBody;
import com.ubikz.scraper.api.controller.request.AbstractRequestBody;
import com.ubikz.scraper.api.controller.request.FeedRequestBody;
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
    public BaseMessage createFeed(FeedRequestBody request) throws Exception {
        return this.handle(() -> this.feedService.createFeed(
                (FeedServiceRequest) this.parseRequest(request, new FeedServiceRequest())
        ), HttpStatus.CREATED, FEED_CREATED);
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public BaseMessage updateFeed(Integer id, FeedRequestBody request) throws Exception {
        return this.handle(() -> {
            FeedServiceRequest serviceRequest = new FeedServiceRequest();
            serviceRequest.setId(id);

            if (id == null) {
                throw new MissingParameterException();
            }

            return this.feedService.updateFeed((FeedServiceRequest) this.parseRequest(request, serviceRequest));
        }, HttpStatus.OK, FEED_UPDATED);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public BaseMessage getFeedById(int id) throws Exception {
        FeedFilterBody filter = new FeedFilterBody();
        filter.setId(id);

        return this.handle(() -> this.feedService.getOneFeed(
                (FeedServiceFilter) this.parseFilter(filter, new FeedServiceFilter())
        ), HttpStatus.OK, FEED_GET_ONE);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public BaseMessage deleteFeedById(int id) throws Exception {
        FeedFilterBody filter = new FeedFilterBody();
        filter.setId(id);

        return this.handle(() -> this.feedService.delete(
                (FeedServiceFilter) this.parseFilter(filter, new FeedServiceFilter())
        ), HttpStatus.OK, FEED_DELETE);
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public BaseMessage getAllFeeds(FeedFilterBody filter) throws Exception {
        return this.handle(() -> this.feedService.getAllFeeds(
                (FeedServiceFilter) this.parseFilter(filter, new FeedServiceFilter())
        ), HttpStatus.OK, FEED_GET_ALL);
    }


    @Override
    protected AbstractServiceRequest parseRequest(AbstractRequestBody data, AbstractServiceRequest request) {
        FeedRequestBody requestBody = (FeedRequestBody) data;
        FeedServiceRequest serviceRequest = (FeedServiceRequest) this.parseBaseRequest(requestBody, request);
        serviceRequest.setUrl(requestBody.getUrl());
        serviceRequest.setFeedTypeId(requestBody.getFeedTypeId());

        return serviceRequest;
    }

    @Override
    protected AbstractServiceFilter parseFilter(AbstractFilterBody data, AbstractServiceFilter filter) {
        return this.parseBaseFilter(data, filter);
    }
}