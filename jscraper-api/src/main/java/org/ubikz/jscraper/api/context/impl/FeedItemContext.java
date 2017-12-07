package org.ubikz.jscraper.api.context.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.context.BaseContext;
import org.ubikz.jscraper.api.controller.model.filter.BaseFilterBody;
import org.ubikz.jscraper.api.controller.model.filter.impl.FeedItemFilterBody;
import org.ubikz.jscraper.api.controller.model.request.BaseRequestBody;
import org.ubikz.jscraper.api.controller.model.request.impl.FeedItemRequestBody;
import org.ubikz.jscraper.api.dto.impl.FeedDto;
import org.ubikz.jscraper.api.service.impl.FeedItemService;
import org.ubikz.jscraper.api.service.impl.FeedService;
import org.ubikz.jscraper.api.service.model.filter.BaseServiceFilter;
import org.ubikz.jscraper.api.service.model.filter.impl.FeedItemServiceFilter;
import org.ubikz.jscraper.api.service.model.filter.impl.FeedServiceFilter;
import org.ubikz.jscraper.api.service.model.message.BaseMessage;
import org.ubikz.jscraper.api.service.model.request.impl.FeedItemServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.FeedListServiceRequest;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class FeedItemContext extends BaseContext<FeedItemService, FeedItemServiceRequest, FeedItemServiceFilter> {
    private FeedService feedService;

    @Autowired
    public FeedItemContext(FeedItemService service, FeedService feedService) {
        this.service = service;
        this.serviceRequest = new FeedItemServiceRequest();
        this.serviceFilter = new FeedItemServiceFilter();
        this.feedService = feedService;
    }

    public BaseMessage generate() {
        FeedListServiceRequest request = new FeedListServiceRequest();
        BaseServiceFilter feedServiceFilter = new FeedServiceFilter();
        feedServiceFilter.setEnabled(true);
        request.setFeedList(feedService.getAll(feedServiceFilter).stream().map(FeedDto.class::cast).collect(Collectors.toList()));

        return toFilterResult(service::generate, HttpStatus.CREATED);
    }

    @Override
    protected <T extends BaseRequestBody> void parseRequest(T data) {
        super.parseRequest(data);
        FeedItemRequestBody requestBody = (FeedItemRequestBody) data;

        serviceRequest.setFeedId(requestBody.getFeedId());
        serviceRequest.setUrl(requestBody.getUrl());
        serviceRequest.setComment(requestBody.getComment());
        serviceRequest.setTagIds(requestBody.getTagIds());
        serviceRequest.setChecksum(requestBody.getChecksum());
        serviceRequest.setApproved(requestBody.getApproved());
        serviceRequest.setReposted(requestBody.getReposted());
        serviceRequest.setViewed(requestBody.getViewed());
        serviceRequest.setSent(requestBody.getReposted());
    }

    @Override
    protected <T extends BaseFilterBody> void parseFilter(T data) {
        super.parseFilter(data);
        FeedItemFilterBody filterBody = (FeedItemFilterBody) data;

        serviceFilter.setUrl(filterBody.getUrl());
        serviceFilter.setChecksum(filterBody.getChecksum());
        serviceFilter.setApproved(filterBody.getApproved());
        serviceFilter.setReposted(filterBody.getReposted());
        serviceFilter.setViewed(filterBody.getViewed());
        serviceFilter.setSent(filterBody.getReposted());

        if (filterBody.getTags() != null) {
            serviceFilter.setTagNames(Arrays.asList(filterBody.getTags()));
        }
    }
}
