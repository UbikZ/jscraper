package org.ubikz.jscraper.api.context.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.context.AbstractContext;
import org.ubikz.jscraper.api.controller.model.filter.AbstractFilterBody;
import org.ubikz.jscraper.api.controller.model.filter.impl.FeedItemFilterBody;
import org.ubikz.jscraper.api.controller.model.request.AbstractRequestBody;
import org.ubikz.jscraper.api.controller.model.request.impl.FeedItemRequestBody;
import org.ubikz.jscraper.api.dto.impl.FeedDto;
import org.ubikz.jscraper.api.service.impl.FeedItemService;
import org.ubikz.jscraper.api.service.impl.FeedService;
import org.ubikz.jscraper.api.service.model.filter.AbstractServiceFilter;
import org.ubikz.jscraper.api.service.model.filter.impl.FeedItemServiceFilter;
import org.ubikz.jscraper.api.service.model.filter.impl.FeedServiceFilter;
import org.ubikz.jscraper.api.service.model.message.BaseMessage;
import org.ubikz.jscraper.api.service.model.request.AbstractServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.FeedItemServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.FeedListServiceRequest;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class FeedItemContext extends AbstractContext {
    private static final int FEED_ITEM_GENERATED = 44;

    private FeedService feedService;

    @Autowired
    public FeedItemContext(FeedItemService feedItemService, FeedService feedService) {
        this.service = feedItemService;
        this.serviceRequest = new FeedItemServiceRequest();
        this.serviceFilter = new FeedItemServiceFilter();
        this.filterBody = new FeedItemFilterBody();
        this.feedService = feedService;

        CREATED = 40;
        UPDATED = 41;
        GET_ONE = 42;
        GET_ALL = 42;
        DELETE = 43;
    }

    /**
     * @return
     * @throws Exception
     */
    public BaseMessage generate() throws Exception {
        return handle(() -> {
            FeedListServiceRequest request = new FeedListServiceRequest();
            AbstractServiceFilter feedServiceFilter = new FeedServiceFilter();
            feedServiceFilter.setEnabled(true);
            request.setFeedList(feedService.getAll(feedServiceFilter).stream().map(FeedDto.class::cast).collect(Collectors.toList()));

            return ((FeedItemService) service).generate(request);
        }, HttpStatus.CREATED, FEED_ITEM_GENERATED);
    }

    @Override
    protected AbstractServiceRequest parseRequest(AbstractRequestBody data, AbstractServiceRequest request) {
        FeedItemRequestBody requestBody = (FeedItemRequestBody) data;
        FeedItemServiceRequest serviceRequest = (FeedItemServiceRequest) parseBaseRequest(requestBody, request);

        serviceRequest.setFeedId(requestBody.getFeedId());
        serviceRequest.setUrl(requestBody.getUrl());
        serviceRequest.setComment(requestBody.getComment());
        serviceRequest.setTagIds(requestBody.getTagIds());
        serviceRequest.setChecksum(requestBody.getChecksum());
        serviceRequest.setApproved(requestBody.getApproved());
        serviceRequest.setReposted(requestBody.getReposted());
        serviceRequest.setViewed(requestBody.getViewed());
        serviceRequest.setSent(requestBody.getReposted());

        return serviceRequest;
    }

    @Override
    protected AbstractServiceFilter parseFilter(AbstractFilterBody data, AbstractServiceFilter filter) throws Exception {
        FeedItemFilterBody filterBody = (FeedItemFilterBody) data;
        FeedItemServiceFilter serviceFilter = (FeedItemServiceFilter) parseBaseFilter(filterBody, filter);

        serviceFilter.setUrl(filterBody.getUrl());
        if (filterBody.getTags() != null) {
            serviceFilter.setTagNames(Arrays.asList(filterBody.getTags()));
        }
        serviceFilter.setChecksum(filterBody.getChecksum());
        serviceFilter.setApproved(filterBody.getApproved());
        serviceFilter.setReposted(filterBody.getReposted());
        serviceFilter.setViewed(filterBody.getViewed());
        serviceFilter.setSent(filterBody.getReposted());

        return serviceFilter;
    }
}