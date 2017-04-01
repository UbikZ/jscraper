package com.ubikz.scraper.core.app.context;

import com.ubikz.scraper.api.controller.filter.AbstractFilterBody;
import com.ubikz.scraper.api.controller.filter.FeedItemFilterBody;
import com.ubikz.scraper.api.controller.request.AbstractRequestBody;
import com.ubikz.scraper.api.controller.request.FeedItemRequestBody;
import com.ubikz.scraper.core.app.dto.FeedDto;
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

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class FeedItemContext extends AbstractContext {
    final private int FEED_ITEM_GENERATED = 44;

    private FeedService feedService;

    @Autowired
    public FeedItemContext(FeedItemService feedItemService, FeedService feedService) {
        this.service = feedItemService;
        this.serviceRequest = new FeedItemServiceRequest();
        this.serviceFilter = new FeedItemServiceFilter();
        this.filterBody = new FeedItemFilterBody();
        this.feedService = feedService;

        this.CREATED = 40;
        this.UPDATED = 41;
        this.GET_ONE = 42;
        this.GET_ALL = 42;
        this.DELETE = 43;
    }

    /**
     * @return
     * @throws Exception
     */
    public BaseMessage generate() throws Exception {
        return this.handle(() -> {
            FeedListServiceRequest request = new FeedListServiceRequest();
            AbstractServiceFilter feedServiceFilter = new FeedServiceFilter();
            feedServiceFilter.setEnabled(true);
            request.setFeedList(
                    this.feedService.getAll(feedServiceFilter)
                            .stream()
                            .map(FeedDto.class::cast)
                            .collect(Collectors.toList())
            );

            return ((FeedItemService) this.service).generate(request);
        }, HttpStatus.CREATED, FEED_ITEM_GENERATED);
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
    protected AbstractServiceFilter parseFilter(AbstractFilterBody data, AbstractServiceFilter filter) throws Exception {
        FeedItemFilterBody filterBody = (FeedItemFilterBody) data;
        FeedItemServiceFilter serviceFilter = (FeedItemServiceFilter) this.parseBaseFilter(filterBody, filter);

        serviceFilter.setUrl(filterBody.getUrl());
        if (filterBody.getTagNames() != null) {
            serviceFilter.setTagNames(Arrays.asList(filterBody.getTagNames()));
        }
        serviceFilter.setChecksum(filterBody.getChecksum());
        serviceFilter.setApproved(filterBody.getApproved());
        serviceFilter.setReposted(filterBody.getReposted());
        serviceFilter.setViewed(filterBody.getViewed());
        serviceFilter.setSent(filterBody.getReposted());

        return serviceFilter;
    }
}