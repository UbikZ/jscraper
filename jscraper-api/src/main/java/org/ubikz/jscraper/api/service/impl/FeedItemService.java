package org.ubikz.jscraper.api.service.impl;

import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.ubikz.jscraper.api.dto.BaseDto;
import org.ubikz.jscraper.api.dto.impl.FeedArticleDto;
import org.ubikz.jscraper.api.dto.impl.FeedDto;
import org.ubikz.jscraper.api.dto.impl.FeedItemDto;
import org.ubikz.jscraper.api.dto.impl.TagDto;
import org.ubikz.jscraper.api.entity.impl.*;
import org.ubikz.jscraper.api.entity.model.filter.impl.*;
import org.ubikz.jscraper.api.entity.model.request.impl.FeedItemEntityRequest;
import org.ubikz.jscraper.api.entity.model.request.impl.TagEntityRequest;
import org.ubikz.jscraper.api.service.BaseService;
import org.ubikz.jscraper.api.service.model.filter.BaseServiceFilter;
import org.ubikz.jscraper.api.service.model.filter.impl.FeedItemServiceFilter;
import org.ubikz.jscraper.api.service.model.request.BaseServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.FeedItemServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.FeedListServiceRequest;
import org.ubikz.jscraper.exception.ApplicativeException;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class FeedItemService extends BaseService<FeedItemEntity, FeedItemEntityRequest, FeedItemEntityFilter, FeedItemDto> {
    private static final String URL_REGEXP = "^(?:http(s)?:\\/\\/)?[\\w.-]+(?:\\.[\\w\\.-]+)+[\\w\\-\\._~:/?#[\\\\]@!\\$&''\\(\\)\\*\\+,;=.]+$";
    private FeedEntity feedEntity;
    private TagEntity tagEntity;
    private FeedProhibitedEntity feedProhibitedEntity;
    private TagProhibitedEntity tagProhibitedEntity;

    @Autowired
    public FeedItemService(FeedItemEntity entity, FeedEntity feedEntity, TagEntity tagEntity, FeedProhibitedEntity feedProhibitedEntity, TagProhibitedEntity tagProhibitedEntity) {
        this.entity = entity;
        this.feedEntity = feedEntity;
        this.tagEntity = tagEntity;
        this.feedProhibitedEntity = feedProhibitedEntity;
        this.tagProhibitedEntity = tagProhibitedEntity;
    }

    @Transactional
    public int generate(FeedListServiceRequest request) throws RuntimeException {
        Map<String, List<FeedArticleDto>> articleMap = new HashMap<>();
        List<FeedItemEntityRequest> entityRequestList = new ArrayList<>();
        List<TagEntityRequest> tagRequestList = new ArrayList<>();

        List<String> feedProhibitedList = feedProhibitedEntity.getAll(new FeedProhibitedEntityFilter()).stream().map(BaseDto::getLabel).collect(Collectors.toList());
        List<String> tagProhibitedList = tagProhibitedEntity.getAll(new TagProhibitedEntityFilter()).stream().map(BaseDto::getLabel).collect(Collectors.toList());

        for (FeedDto feed : request.getFeedList()) {
            FeedEntityFilter filter = new FeedEntityFilter();
            filter.setUrl(feed.getUrl());
            filter.setUrlRegex(feed.getFeedTypeDto().getUrlRegex());
            filter.setProhibitedFeedList(feedProhibitedList);
            filter.setProhibitedTagList(tagProhibitedList);

            List<FeedArticleDto> subList;

            try {
                subList = feedEntity.getRssFeedArticleList(filter);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            articleMap.put(feed.getUrl(), subList);
            entityRequestList.addAll(parseServiceToEntityArticleListRequest(feed, subList));
            subList.forEach(articleDto -> tagRequestList.addAll(articleDto.getTagList().stream().map(TagEntityRequest::new).collect(Collectors.toList())));
        }

        if (tagRequestList.size() > 0) {
            tagEntity.createAll(tagRequestList);
        }

        if (entityRequestList.size() > 0) {
            populateEntityArticleListRequestWithTags(entityRequestList, tagEntity.getAllMappedBy(new TagEntityFilter(), "label"));

            entity.createAll(entityRequestList);
        }

        return articleMap.size();
    }

    private void populateEntityArticleListRequestWithTags(List<FeedItemEntityRequest> requestList, Map<Object, TagDto> tagList) {
        requestList.forEach(request -> request.setTagIds(request.getTagNames().stream().map(tagList::get).filter(Objects::nonNull).map(TagDto::getId).collect(Collectors.toList())));
    }

    private List<FeedItemEntityRequest> parseServiceToEntityArticleListRequest(FeedDto feed, List<FeedArticleDto> articleList) {
        List<FeedItemEntityRequest> request = new ArrayList<>();

        articleList.forEach(article -> article.getPictureList().stream().filter(l -> !l.isEmpty()).findFirst().ifPresent(imageUrl -> {
            if (!Strings.isNullOrEmpty(imageUrl) && imageUrl.matches(URL_REGEXP)) {
                FeedItemEntityRequest entityRequest = new FeedItemEntityRequest();
                entityRequest.setFeedId(feed.getId());
                entityRequest.setLabel(article.getLabel());
                entityRequest.setUrl(imageUrl);
                entityRequest.setTagNames(article.getTagList());

                try {
                    entityRequest.setChecksum(DigestUtils.md5DigestAsHex(entityRequest.getUrl().getBytes("UTF-8")));
                } catch (UnsupportedEncodingException e) {
                    throw new ApplicativeException(e);
                }

                request.add(entityRequest);
            }
        }));

        return request;
    }

    @Override
    protected <T extends BaseServiceRequest> void parseRequest(T data) {
        super.parseRequest(data);
        FeedItemServiceRequest serviceRequest = (FeedItemServiceRequest) data;

        entityRequest.setUrl(serviceRequest.getUrl());
        entityRequest.setComment(serviceRequest.getComment());
        entityRequest.setFeedId(serviceRequest.getFeedId());
        entityRequest.setChecksum(serviceRequest.getChecksum());
        entityRequest.setTagIds(serviceRequest.getTagIds());
        entityRequest.setViewed(serviceRequest.getViewed());
        entityRequest.setApproved(serviceRequest.getApproved());
        entityRequest.setReposted(serviceRequest.getReposted());
        entityRequest.setSent(serviceRequest.getSent());
    }

    @Override
    protected <T extends BaseServiceFilter> void parseFilter(T data) {
        super.parseFilter(data);
        FeedItemServiceFilter serviceFilter = (FeedItemServiceFilter) data;

        entityFilter.setUrl(serviceFilter.getUrl());
        entityFilter.setFeedId(serviceFilter.getFeedId());
        entityFilter.setChecksum(serviceFilter.getChecksum());
        entityFilter.setViewed(serviceFilter.getViewed());
        entityFilter.setApproved(serviceFilter.getApproved());
        entityFilter.setReposted(serviceFilter.getReposted());
        entityFilter.setSent(serviceFilter.getSent());
        entityFilter.setTagNames(serviceFilter.getTagNames());
    }
}
