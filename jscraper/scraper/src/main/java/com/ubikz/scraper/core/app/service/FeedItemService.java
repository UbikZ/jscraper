package com.ubikz.scraper.core.app.service;

import com.ubikz.scraper.core.app.dto.*;
import com.ubikz.scraper.core.app.entity.FeedEntity;
import com.ubikz.scraper.core.app.entity.FeedItemEntity;
import com.ubikz.scraper.core.app.entity.FeedProhibitedEntity;
import com.ubikz.scraper.core.app.entity.TagProhibitedEntity;
import com.ubikz.scraper.core.app.entity.filter.*;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import com.ubikz.scraper.core.app.entity.request.FeedItemEntityRequest;
import com.ubikz.scraper.core.app.service.filter.AbstractServiceFilter;
import com.ubikz.scraper.core.app.service.filter.FeedItemServiceFilter;
import com.ubikz.scraper.core.app.service.request.AbstractServiceRequest;
import com.ubikz.scraper.core.app.service.request.FeedItemServiceRequest;
import com.ubikz.scraper.core.app.service.request.FeedListServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FeedItemService extends AbstractService {
    private FeedItemEntity feedItemEntity;
    private FeedEntity feedEntity;
    private FeedProhibitedEntity feedProhibitedEntity;
    private TagProhibitedEntity tagProhibitedEntity;

    @Autowired
    public FeedItemService(
            FeedItemEntity feedItemEntity,
            FeedEntity feedEntity,
            FeedProhibitedEntity feedProhibitedEntity,
            TagProhibitedEntity tagProhibitedEntity) {
        this.feedItemEntity = feedItemEntity;
        this.feedEntity = feedEntity;
        this.feedProhibitedEntity = feedProhibitedEntity;
        this.tagProhibitedEntity = tagProhibitedEntity;
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public Map<String, List<FeedArticleDto>> generate(FeedListServiceRequest request) throws Exception {
        Map<String, List<FeedArticleDto>> articleMap = new HashMap<>();
        List<FeedItemEntityRequest> entityRequestList = new ArrayList<>();

        List<String> feedProhibitedList = this
                .feedProhibitedEntity
                .getAllFeedsProhibited(new FeedProhibitedEntityFilter())
                .stream()
                .map(FeedProhibitedDto::getLabel)
                .collect(Collectors.toList());
        List<String> tagProhibitedList = this
                .tagProhibitedEntity
                .getAllTagsProhibited(new TagProhibitedEntityFilter())
                .stream()
                .map(TagProhibitedDto::getLabel)
                .collect(Collectors.toList());

        for (FeedDto feed : request.getFeedList()) {
            FeedEntityFilter filter = new FeedEntityFilter();
            filter.setUrl(feed.getUrl());
            filter.setUrlRegex(feed.getFeedTypeDto().getUrlRegex());
            filter.setProhibitedFeedList(feedProhibitedList);
            filter.setProhibitedTagList(tagProhibitedList);

            List<FeedArticleDto> subList = this.feedEntity.getRssFeedArticleList(filter);

            articleMap.put(feed.getUrl(), subList);
            entityRequestList.addAll(this.parseServiceToEntityArticleListRequest(feed, subList));
        }

        this.feedItemEntity.createFeedItems(entityRequestList);

        return articleMap;
    }

    /**
     * @param feed
     * @param articleList
     * @return
     */
    private List<FeedItemEntityRequest> parseServiceToEntityArticleListRequest(
            FeedDto feed,
            List<FeedArticleDto> articleList
    ) throws UnsupportedEncodingException {
        List<FeedItemEntityRequest> request = new ArrayList<>();

        for (FeedArticleDto articleDto : articleList) {
            FeedItemEntityRequest entityRequest = new FeedItemEntityRequest();
            entityRequest.setFeedId(feed.getId());
            entityRequest.setLabel(articleDto.getLabel());
            entityRequest.setUrl(articleDto.getPictureList().get(0));
            entityRequest.setChecksum(DigestUtils.md5DigestAsHex(entityRequest.getUrl().getBytes("UTF-8")));
            request.add(entityRequest);
        }

        return request;
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public List<FeedItemDto> getAllFeedItems(FeedItemServiceFilter filter) throws Exception {
        return this.feedItemEntity.getAllFeedItems(
                (FeedItemEntityFilter) this.parseServiceToEntityFilter(filter)
        );
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public FeedItemDto getOneFeedItem(FeedItemServiceFilter filter) throws Exception {
        return this.feedItemEntity.getOneFeedItem(
                (FeedItemEntityFilter) this.parseServiceToEntityFilter(filter)
        );
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public int delete(FeedItemServiceFilter filter) throws Exception {
        return this.feedItemEntity.deleteFeedItem(
                (FeedItemEntityFilter) this.parseServiceToEntityFilter(filter)
        );
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public int createFeedItem(FeedItemServiceRequest request) throws Exception {
        return this.feedItemEntity.createFeedItem((FeedItemEntityRequest) this.parseServiceToEntityRequest(request));
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public int updateFeedItem(FeedItemServiceRequest request) throws Exception {
        return this.feedItemEntity.updateFeedItem((FeedItemEntityRequest) this.parseServiceToEntityRequest(request));
    }

    /**
     * @param request
     * @return
     */
    @Override
    protected AbstractEntityRequest parseServiceToEntityRequest(AbstractServiceRequest request) {
        FeedItemEntityRequest feedItemEntityRequest = new FeedItemEntityRequest();
        FeedItemServiceRequest feedItemServiceRequest = (FeedItemServiceRequest) request;

        this.parseBaseServiceToEntityRequest(feedItemServiceRequest, feedItemEntityRequest);
        feedItemEntityRequest.setUrl(feedItemServiceRequest.getUrl());
        feedItemEntityRequest.setFeedId(feedItemServiceRequest.getFeedId());
        feedItemEntityRequest.setChecksum(feedItemServiceRequest.getChecksum());
        feedItemEntityRequest.setTagIds(feedItemServiceRequest.getTagIds());
        feedItemEntityRequest.setViewed(feedItemServiceRequest.getViewed());
        feedItemEntityRequest.setApproved(feedItemServiceRequest.getApproved());
        feedItemEntityRequest.setReposted(feedItemServiceRequest.getReposted());
        feedItemEntityRequest.setSent(feedItemServiceRequest.getSent());

        return feedItemEntityRequest;
    }

    /**
     * @param filter
     * @return
     */
    @Override
    protected AbstractEntityFilter parseServiceToEntityFilter(AbstractServiceFilter filter) {
        FeedItemEntityFilter feedItemEntityFilter = new FeedItemEntityFilter();
        FeedItemServiceFilter feedItemServiceFilter = (FeedItemServiceFilter) filter;

        this.parseBaseServiceToEntityFilter(feedItemServiceFilter, feedItemEntityFilter);
        feedItemEntityFilter.setUrl(feedItemServiceFilter.getUrl());
        feedItemEntityFilter.setFeedId(feedItemServiceFilter.getFeedId());
        feedItemEntityFilter.setChecksum(feedItemServiceFilter.getChecksum());
        feedItemEntityFilter.setViewed(feedItemServiceFilter.getViewed());
        feedItemEntityFilter.setApproved(feedItemServiceFilter.getApproved());
        feedItemEntityFilter.setReposted(feedItemServiceFilter.getReposted());
        feedItemEntityFilter.setSent(feedItemServiceFilter.getSent());

        return feedItemEntityFilter;
    }
}