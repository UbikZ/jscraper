package com.ubikz.scraper.core.app.service;

import com.ubikz.scraper.core.app.dto.AbstractDto;
import com.ubikz.scraper.core.app.dto.FeedArticleDto;
import com.ubikz.scraper.core.app.dto.FeedDto;
import com.ubikz.scraper.core.app.entity.*;
import com.ubikz.scraper.core.app.entity.filter.*;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import com.ubikz.scraper.core.app.entity.request.FeedItemEntityRequest;
import com.ubikz.scraper.core.app.entity.request.TagEntityRequest;
import com.ubikz.scraper.core.app.service.filter.AbstractServiceFilter;
import com.ubikz.scraper.core.app.service.filter.FeedItemServiceFilter;
import com.ubikz.scraper.core.app.service.request.AbstractServiceRequest;
import com.ubikz.scraper.core.app.service.request.FeedItemServiceRequest;
import com.ubikz.scraper.core.app.service.request.FeedListServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FeedItemService extends AbstractService {
    private FeedEntity feedEntity;
    private TagEntity tagEntity;
    private FeedProhibitedEntity feedProhibitedEntity;
    private TagProhibitedEntity tagProhibitedEntity;

    @Autowired
    public FeedItemService(
            FeedItemEntity feedItemEntity,
            FeedEntity feedEntity,
            TagEntity tagEntity,
            FeedProhibitedEntity feedProhibitedEntity,
            TagProhibitedEntity tagProhibitedEntity) {
        this.entity = feedItemEntity;
        this.feedEntity = feedEntity;
        this.tagEntity = tagEntity;
        this.feedProhibitedEntity = feedProhibitedEntity;
        this.tagProhibitedEntity = tagProhibitedEntity;
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    @Transactional
    public Map<String, List<FeedArticleDto>> generate(FeedListServiceRequest request) throws Exception {
        Map<String, List<FeedArticleDto>> articleMap = new HashMap<>();
        List<AbstractEntityRequest> entityRequestList = new ArrayList<>();
        List<AbstractEntityRequest> tagRequestList = new ArrayList<>();

        List<String> feedProhibitedList = this
                .feedProhibitedEntity
                .getAll(new FeedProhibitedEntityFilter())
                .stream()
                .map(AbstractDto::getLabel)
                .collect(Collectors.toList());
        List<String> tagProhibitedList = this
                .tagProhibitedEntity
                .getAll(new TagProhibitedEntityFilter())
                .stream()
                .map(AbstractDto::getLabel)
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

            for (FeedArticleDto articleDto : subList) {
                tagRequestList.addAll(articleDto.getTagList()
                        .stream()
                        .map(tag -> {
                            TagEntityRequest tagRequest = new TagEntityRequest();
                            tagRequest.setLabel(tag);
                            return tagRequest;
                        })
                        .collect(Collectors.toList())
                );
            }
        }

        this.tagEntity.createAll(tagRequestList);
        this.populateEntityArticleListRequestWithTags(
                entityRequestList,
                this.tagEntity.getAllMappedBy(new TagEntityFilter(), "label")
        );

        this.entity.createAll(entityRequestList);

        return articleMap;
    }

    /**
     * @param requestList
     * @param tagList
     */
    private void populateEntityArticleListRequestWithTags(
            List<AbstractEntityRequest> requestList,
            Map<Object, AbstractDto> tagList
    ) {
        for (AbstractEntityRequest request : requestList) {
            FeedItemEntityRequest fiRequest = (FeedItemEntityRequest) request;

            fiRequest.setTagIds(
                    fiRequest.getTagNames()
                            .stream()
                            .map(name -> tagList.get(name).getId())
                            .collect(Collectors.toList())
            );
        }
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
            List<String> pictureList = articleDto.getPictureList();

            if (pictureList != null && pictureList.size() > 0) {
                FeedItemEntityRequest entityRequest = new FeedItemEntityRequest();
                entityRequest.setFeedId(feed.getId());
                entityRequest.setLabel(articleDto.getLabel());
                entityRequest.setUrl(articleDto.getPictureList().get(0));
                entityRequest.setTagNames(articleDto.getTagList());
                entityRequest.setChecksum(DigestUtils.md5DigestAsHex(entityRequest.getUrl().getBytes("UTF-8")));
                request.add(entityRequest);
            }
        }

        return request;
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
        feedItemEntityRequest.setComment(feedItemEntityRequest.getComment());
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
        feedItemEntityFilter.setTagNames(feedItemServiceFilter.getTagNames());

        return feedItemEntityFilter;
    }
}