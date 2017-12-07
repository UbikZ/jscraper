package org.ubikz.jscraper.api.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.ubikz.jscraper.api.dal.impl.FeedDal;
import org.ubikz.jscraper.api.dal.impl.FeedItemDal;
import org.ubikz.jscraper.api.dal.impl.TagDal;
import org.ubikz.jscraper.api.dal.model.filter.impl.FeedDalFilter;
import org.ubikz.jscraper.api.dal.model.filter.impl.FeedItemDalFilter;
import org.ubikz.jscraper.api.dal.model.filter.impl.TagDalFilter;
import org.ubikz.jscraper.api.dal.model.request.impl.FeedItemDalRequest;
import org.ubikz.jscraper.api.dal.model.request.impl.FeedItemTagDalRequest;
import org.ubikz.jscraper.api.dto.BaseDto;
import org.ubikz.jscraper.api.dto.impl.FeedDto;
import org.ubikz.jscraper.api.dto.impl.FeedItemDto;
import org.ubikz.jscraper.api.dto.impl.TagDto;
import org.ubikz.jscraper.api.entity.BaseEntity;
import org.ubikz.jscraper.api.entity.helper.impl.FeedEntityHelper;
import org.ubikz.jscraper.api.entity.helper.impl.FeedItemEntityHelper;
import org.ubikz.jscraper.api.entity.helper.impl.TagEntityHelper;
import org.ubikz.jscraper.api.entity.model.filter.BaseEntityFilter;
import org.ubikz.jscraper.api.entity.model.filter.impl.FeedItemEntityFilter;
import org.ubikz.jscraper.api.entity.model.request.BaseEntityRequest;
import org.ubikz.jscraper.api.entity.model.request.impl.FeedItemEntityRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FeedItemEntity extends BaseEntity<FeedItemDal, FeedItemDalRequest, FeedItemDalFilter, FeedItemEntityHelper, FeedItemDto> {
    private FeedDal feedDal;
    private TagDal tagDal;
    private FeedEntityHelper feedHelper;
    private TagEntityHelper tagHelper;

    @Autowired
    public FeedItemEntity(FeedItemDal dal, FeedDal feedDal, TagDal tagDal) {
        this.dal = dal;
        this.feedDal = feedDal;
        this.tagDal = tagDal;
        this.dalRequest = new FeedItemDalRequest();
        this.dalFilter = new FeedItemDalFilter();
        this.helper = new FeedItemEntityHelper();
        this.feedHelper = new FeedEntityHelper();
        this.tagHelper = new TagEntityHelper();
    }

    @Override
    public <T extends BaseEntityRequest> List<FeedItemDto> createAll(List<T> requestList) {
        parseListRequest(requestList);
        List<FeedItemDto> list = helper.getDtoListFromDal(dal.createAll(dalRequest), "id");

        FeedItemEntityFilter filter = new FeedItemEntityFilter();
        filter.setIdList(list.stream().map(BaseDto::getId).collect(Collectors.toList()));

        if (filter.getIdList() != null && filter.getIdList().size() > 0) {
            dal.createTags(parseListEntityToTagDalRequest(getAllMappedBy(filter, "url"), requestList));
        }

        return list;
    }

    @Transactional
    public <T extends BaseEntityRequest> int create(T request) {
        FeedItemEntityRequest entityRequest = (FeedItemEntityRequest) request;
        parseRequest(entityRequest);
        int createdId = dal.create(dalRequest);

        if (entityRequest.getTagIds() != null) {
            dal.createTags(parseTagRequest(createdId, entityRequest));
        }

        return createdId;
    }

    @Override
    protected void computeLoading(List<FeedItemDto> feedItems) {
        FeedDalFilter feedDalFilter = new FeedDalFilter();
        TagDalFilter tagDalFilter = new TagDalFilter();
        Map<Integer, FeedDto> feedMap;
        Map<Integer, TagDto> tagMap;

        feedDalFilter.setIdList(new ArrayList<>());
        tagDalFilter.setIdList(new ArrayList<>());

        // Build queries
        for (FeedItemDto feedItem : feedItems) {
            feedDalFilter.getIdList().add(feedItem.getFeed().getId());
            if (feedItem.getTags() != null) {
                tagDalFilter.getIdList().addAll(feedItem.getTags().stream().map(TagDto::getId).collect(Collectors.toList()));
            }
        }

        // Execute queries
        feedMap = this.feedHelper
                .getDtoMapFromDal(this.feedDal.getAll(feedDalFilter), "id")
                .entrySet().stream()
                .collect(Collectors.toMap(p -> (int) p.getKey(), p -> (FeedDto) p.getValue()));

        tagMap = this.tagHelper
                .getDtoMapFromDal(this.tagDal.getAll(tagDalFilter), "id")
                .entrySet().stream()
                .collect(Collectors.toMap(p -> (int) p.getKey(), p -> (TagDto) p.getValue()));

        for (FeedItemDto feedItem : feedItems) {
            if (feedMap != null && feedMap.containsKey(feedItem.getFeed().getId())) {
                feedItem.setFeed(feedMap.get(feedItem.getFeed().getId()));
            }

            if (tagMap != null && feedItem.getTags() != null) {
                List<TagDto> tags = new ArrayList<>();
                for (TagDto tag : feedItem.getTags()) {
                    if (tagMap.containsKey(tag.getId())) {
                        tags.add(tagMap.get(tag.getId()));
                    }
                }

                feedItem.setTags(tags);
            }
        }
    }

    @Override
    protected void computeLoading(Map<Object, FeedItemDto> dtoList) {}

    @Override
    protected <T extends BaseEntityRequest> void parseRequest(T request) {
        super.parseRequest(request);
        FeedItemEntityRequest feedItemEntityRequest = (FeedItemEntityRequest) request;

        dalRequest.setFeedId(feedItemEntityRequest.getFeedId());
        dalRequest.setUrl(feedItemEntityRequest.getUrl());
        dalRequest.setComment(feedItemEntityRequest.getComment());
        dalRequest.setChecksum(feedItemEntityRequest.getChecksum());
        dalRequest.setApproved(feedItemEntityRequest.getApproved());
        dalRequest.setReposted(feedItemEntityRequest.getReposted());
        dalRequest.setViewed(feedItemEntityRequest.getViewed());
        dalRequest.setSent(feedItemEntityRequest.getReposted());
    }

    @Override
    protected <T extends BaseEntityFilter> void parseFilter(T filter) {
        super.parseFilter(filter);
        FeedItemEntityFilter feedItemEntityFilter = (FeedItemEntityFilter) filter;

        dalFilter.setFeedId(feedItemEntityFilter.getFeedId());
        dalFilter.setUrl(feedItemEntityFilter.getUrl());
        dalFilter.setChecksum(feedItemEntityFilter.getChecksum());
        dalFilter.setApproved(feedItemEntityFilter.getApproved());
        dalFilter.setReposted(feedItemEntityFilter.getReposted());
        dalFilter.setViewed(feedItemEntityFilter.getViewed());
        dalFilter.setSent(feedItemEntityFilter.getReposted());
        dalFilter.setTagNames(feedItemEntityFilter.getTagNames());
    }

    private List<FeedItemTagDalRequest> parseTagRequest(int id, FeedItemEntityRequest request) {
        List<FeedItemTagDalRequest> tagRequestList = new ArrayList<>();

        for (Integer tagId : request.getTagIds()) {
            FeedItemTagDalRequest feedItemTagDalRequest = new FeedItemTagDalRequest();
            feedItemTagDalRequest.setFeedItemId(id);
            feedItemTagDalRequest.setTagId(tagId);
            tagRequestList.add(feedItemTagDalRequest);
        }

        return tagRequestList;
    }

    private List<FeedItemTagDalRequest> parseListEntityToTagDalRequest(Map<Object, FeedItemDto> abstractMap, List<FeedItemEntityRequest> requestList) {
        List<FeedItemTagDalRequest> tagRequestList = new ArrayList<>();

        requestList.forEach(r -> {
            FeedItemDto dto = abstractMap.get(r.getUrl());
            if (dto != null) {
                tagRequestList.addAll(this.parseTagRequest(dto.getId(), r));
            }
        });

        return tagRequestList;
    }
}
