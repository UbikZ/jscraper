package com.ubikz.scraper.core.app.context;

import com.ubikz.scraper.api.controller.filter.AbstractFilterBody;
import com.ubikz.scraper.api.controller.filter.FeedItemFilterBody;
import com.ubikz.scraper.api.controller.request.AbstractRequestBody;
import com.ubikz.scraper.api.controller.request.FeedItemRequestBody;
import com.ubikz.scraper.core.app.exception.MissingParameterException;
import com.ubikz.scraper.core.app.service.FeedItemService;
import com.ubikz.scraper.core.app.service.FeedService;
import com.ubikz.scraper.core.app.service.filter.AbstractServiceFilter;
import com.ubikz.scraper.core.app.service.filter.FeedItemServiceFilter;
import com.ubikz.scraper.core.app.service.filter.FeedServiceFilter;
import com.ubikz.scraper.core.app.service.message.BaseMessage;
import com.ubikz.scraper.core.app.service.request.AbstractServiceRequest;
import com.ubikz.scraper.core.app.service.request.FeedItemServiceRequest;
import com.ubikz.scraper.core.app.service.request.FeedListServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class FeedItemContext extends AbstractContext {
    final private int FEED_ITEM_CREATED = 40;
    final private int FEED_ITEM_UPDATED = 41;
    final private int FEED_ITEM_GET_ONE = 42;
    final private int FEED_ITEM_GET_ALL = 42;
    final private int FEED_ITEM_DELETE = 43;
    final private int FEED_ITEM_GENERATED = 44;

    private FeedItemService feedItemService;
    private FeedService feedService;

    @Autowired
    public FeedItemContext(FeedItemService feedItemService, FeedService feedService) {
        this.feedItemService = feedItemService;
        this.feedService = feedService;
    }

    /**
     * @return
     * @throws Exception
     */
    public BaseMessage generate() throws Exception {
        return this.handle(() -> {
            FeedListServiceRequest request = new FeedListServiceRequest();
            FeedServiceFilter feedServiceFilter = new FeedServiceFilter();
            feedServiceFilter.setEnabled(true);
            feedServiceFilter.setLazy(true);
            request.setFeedList(this.feedService.getAllFeeds(feedServiceFilter));

            return this.feedItemService.generate(request);
        }, HttpStatus.CREATED, FEED_ITEM_GENERATED);
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public BaseMessage createFeedItem(FeedItemRequestBody request) throws Exception {
        return this.handle(() -> this.feedItemService.createFeedItem(
                (FeedItemServiceRequest) this.parseRequest(request, new FeedItemServiceRequest())
        ), HttpStatus.CREATED, FEED_ITEM_CREATED);
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public BaseMessage updateFeedItem(Integer id, FeedItemRequestBody request) throws Exception {
        return this.handle(() -> {
            FeedItemServiceRequest serviceRequest = new FeedItemServiceRequest();
            serviceRequest.setId(id);

            if (id == null) {
                throw new MissingParameterException();
            }

            return this.feedItemService.updateFeedItem(
                    (FeedItemServiceRequest) this.parseRequest(request, serviceRequest)
            );
        }, HttpStatus.OK, FEED_ITEM_UPDATED);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public BaseMessage getFeedItemById(int id) throws Exception {
        FeedItemFilterBody filter = new FeedItemFilterBody();
        filter.setId(id);

        return this.handle(() -> this.feedItemService.getOneFeedItem(
                (FeedItemServiceFilter) this.parseFilter(filter, new FeedItemServiceFilter())
        ), HttpStatus.OK, FEED_ITEM_GET_ONE);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public BaseMessage deleteFeedItemById(int id) throws Exception {
        FeedItemFilterBody filter = new FeedItemFilterBody();
        filter.setId(id);

        return this.handle(() -> this.feedItemService.delete(
                (FeedItemServiceFilter) this.parseFilter(filter, new FeedItemServiceFilter())
        ), HttpStatus.OK, FEED_ITEM_DELETE);
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public BaseMessage getAllFeedItems(FeedItemFilterBody filter) throws Exception {
        return this.handle(() -> this.feedItemService.getAllFeedItems(
                (FeedItemServiceFilter) this.parseFilter(filter, new FeedItemServiceFilter())
        ), HttpStatus.OK, FEED_ITEM_GET_ALL);
    }

    @Override
    protected AbstractServiceRequest parseRequest(AbstractRequestBody data, AbstractServiceRequest request) {
        FeedItemRequestBody requestBody = (FeedItemRequestBody) data;
        FeedItemServiceRequest serviceRequest = (FeedItemServiceRequest) this.parseBaseRequest(requestBody, request);

        serviceRequest.setFeedId(requestBody.getFeedId());
        serviceRequest.setUrl(requestBody.getUrl());
        serviceRequest.setTagIds(requestBody.getTagIds());
        serviceRequest.setChecksum(requestBody.getChecksum());
        serviceRequest.setApproved(requestBody.getApproved());
        serviceRequest.setReposted(requestBody.getReposted());
        serviceRequest.setViewed(requestBody.getViewed());
        serviceRequest.setSent(requestBody.getReposted());

        return serviceRequest;
    }

    @Override
    protected AbstractServiceFilter parseFilter(AbstractFilterBody data, AbstractServiceFilter filter) {
        FeedItemFilterBody filterBody = (FeedItemFilterBody) data;
        FeedItemServiceFilter serviceFilter = (FeedItemServiceFilter) this.parseBaseFilter(filterBody, filter);

        serviceFilter.setUrl(filterBody.getUrl());
        serviceFilter.setTagNames(filterBody.getTagNames());
        serviceFilter.setChecksum(filterBody.getChecksum());
        serviceFilter.setApproved(filterBody.getApproved());
        serviceFilter.setReposted(filterBody.getReposted());
        serviceFilter.setViewed(filterBody.getViewed());
        serviceFilter.setSent(filterBody.getReposted());
        serviceFilter.setStartDate(filterBody.getStartDate());
        serviceFilter.setEndDate(filterBody.getEndDate());

        return serviceFilter;
    }
}