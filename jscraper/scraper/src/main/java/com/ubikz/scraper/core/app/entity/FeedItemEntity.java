package com.ubikz.scraper.core.app.entity;

import com.ubikz.scraper.core.app.dal.FeedDal;
import com.ubikz.scraper.core.app.dal.FeedItemDal;
import com.ubikz.scraper.core.app.dal.TagDal;
import com.ubikz.scraper.core.app.dal.filter.AbstractDalFilter;
import com.ubikz.scraper.core.app.dal.filter.FeedDalFilter;
import com.ubikz.scraper.core.app.dal.filter.FeedItemDalFilter;
import com.ubikz.scraper.core.app.dal.filter.TagDalFilter;
import com.ubikz.scraper.core.app.dal.request.AbstractDalRequest;
import com.ubikz.scraper.core.app.dal.request.FeedItemDalRequest;
import com.ubikz.scraper.core.app.dal.request.FeedItemTagDalRequest;
import com.ubikz.scraper.core.app.dto.AbstractDto;
import com.ubikz.scraper.core.app.dto.FeedDto;
import com.ubikz.scraper.core.app.dto.FeedItemDto;
import com.ubikz.scraper.core.app.dto.TagDto;
import com.ubikz.scraper.core.app.entity.filter.AbstractEntityFilter;
import com.ubikz.scraper.core.app.entity.filter.FeedItemEntityFilter;
import com.ubikz.scraper.core.app.entity.helper.FeedEntityHelper;
import com.ubikz.scraper.core.app.entity.helper.FeedItemEntityHelper;
import com.ubikz.scraper.core.app.entity.helper.TagEntityHelper;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import com.ubikz.scraper.core.app.entity.request.FeedItemEntityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FeedItemEntity extends AbstractEntity {
    private FeedDal feedDal;
    private TagDal tagDal;
    private FeedEntityHelper feedHelper;
    private TagEntityHelper tagHelper;

    @Autowired
    public FeedItemEntity(FeedItemDal feedItemDal, FeedDal feedDal, TagDal tagDal) {
        this.dal = feedItemDal;
        this.feedDal = feedDal;
        this.tagDal = tagDal;
        this.helper = new FeedItemEntityHelper();
        this.feedHelper = new FeedEntityHelper();
        this.tagHelper = new TagEntityHelper();
    }

    /**
     * @param request
     * @return
     */
    @Transactional
    public int createAllWithTags(FeedItemEntityRequest request) {
        int createdId = this.dal.create(this.parseEntityToDalRequest(request));

        if (request.getTagIds() != null) {
            ((FeedItemDal) this.dal).createTags(this.parseEntityToTagDalRequest(createdId, request));
        }

        return createdId;
    }

    /**
     * @param feedItems
     */
    @Override
    protected void computeLoading(List<AbstractDto> feedItems) throws Exception {
        FeedDalFilter feedDalFilter = new FeedDalFilter();
        TagDalFilter tagDalFilter = new TagDalFilter();
        Map<Integer, FeedDto> feedMap;
        Map<Integer, TagDto> tagMap;

        feedDalFilter.setIdList(new ArrayList<>());
        tagDalFilter.setIdList(new ArrayList<>());

        // Build queries
        for (AbstractDto dto : feedItems) {
            FeedItemDto feedItem = (FeedItemDto) dto;

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

        for (AbstractDto dto : feedItems) {
            FeedItemDto feedItem = (FeedItemDto) dto;

            if (feedMap != null && feedMap.containsKey(feedItem.getFeed().getId())) {
                feedItem.setFeed(feedMap.get(feedItem.getFeed().getId()));
            }

            if (tagMap != null) {
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
    protected void computeLoading(Map<Object, AbstractDto> dtoList) throws Exception {
    }

    /**
     * @param requestList
     * @return
     */
    public List<AbstractDto> createAll(List<AbstractEntityRequest> requestList) throws Exception {
        List<AbstractDto> list = this.helper.getDtoListFromReturnDal(
                this.dal.createAll(this.parseListEntityToDalRequest(requestList)),
                "id"
        );

        FeedItemEntityFilter filter = new FeedItemEntityFilter();
        filter.setIdList(list.stream().map(AbstractDto::getId).collect(Collectors.toList()));

        if (filter.getIdList() != null && filter.getIdList().size() > 0) {
            ((FeedItemDal) this.dal).createTags(this.parseListEntityToTagDalRequest(
                    this.getAllMappedBy(filter, "url"),
                    requestList
            ));
        }

        return list;
    }

    /**
     * @param request
     * @return
     */
    @Transactional
    public int create(AbstractEntityRequest request) {
        FeedItemEntityRequest entityRequest = (FeedItemEntityRequest) request;
        int createdId = this.dal.create(this.parseEntityToDalRequest(entityRequest));

        if (entityRequest.getTagIds() != null) {
            ((FeedItemDal) this.dal).createTags(this.parseEntityToTagDalRequest(createdId, entityRequest));
        }

        return createdId;
    }

    /**
     * @param id
     * @param request
     * @return
     */
    private List<FeedItemTagDalRequest> parseEntityToTagDalRequest(int id, FeedItemEntityRequest request) {
        List<FeedItemTagDalRequest> tagRequestList = new ArrayList<>();

        for (Integer tagId : request.getTagIds()) {
            FeedItemTagDalRequest feedItemTagDalRequest = new FeedItemTagDalRequest();
            feedItemTagDalRequest.setFeedItemId(id);
            feedItemTagDalRequest.setTagId(tagId);
            tagRequestList.add(feedItemTagDalRequest);
        }

        return tagRequestList;
    }

    /**
     * @param abstractMap
     * @param requestList
     * @return
     */
    private List<FeedItemTagDalRequest> parseListEntityToTagDalRequest(
            Map<Object, AbstractDto> abstractMap,
            List<AbstractEntityRequest> requestList
    ) {
        List<FeedItemTagDalRequest> tagRequestList = new ArrayList<>();

        for (AbstractEntityRequest request : requestList) {
            FeedItemEntityRequest fiRequest = (FeedItemEntityRequest) request;
            AbstractDto current = abstractMap.get(fiRequest.getUrl());

            tagRequestList.addAll(this.parseEntityToTagDalRequest(current.getId(), fiRequest));
        }

        return tagRequestList;
    }

    /**
     * @param request
     * @return
     */
    @Override
    protected AbstractDalRequest parseEntityToDalRequest(AbstractEntityRequest request) {
        FeedItemDalRequest feedItemDalRequest = new FeedItemDalRequest();
        FeedItemEntityRequest feedItemEntityRequest = (FeedItemEntityRequest) request;

        feedItemDalRequest = (FeedItemDalRequest) this.parseBaseEntityToDalRequest(feedItemEntityRequest, feedItemDalRequest);
        feedItemDalRequest.setFeedId(feedItemEntityRequest.getFeedId());
        feedItemDalRequest.setUrl(feedItemEntityRequest.getUrl());
        feedItemDalRequest.setChecksum(feedItemEntityRequest.getChecksum());
        feedItemDalRequest.setApproved(feedItemEntityRequest.getApproved());
        feedItemDalRequest.setReposted(feedItemEntityRequest.getReposted());
        feedItemDalRequest.setViewed(feedItemEntityRequest.getViewed());
        feedItemDalRequest.setSent(feedItemEntityRequest.getReposted());

        return feedItemDalRequest;
    }

    /**
     * @param filter
     * @return
     */
    @Override
    protected AbstractDalFilter parseEntityToDalFilter(AbstractEntityFilter filter) {
        FeedItemDalFilter feedItemDalFilter = new FeedItemDalFilter();
        FeedItemEntityFilter feedItemEntityFilter = (FeedItemEntityFilter) filter;

        feedItemDalFilter = (FeedItemDalFilter) this.parseBaseEntityToDalFilter(feedItemEntityFilter, feedItemDalFilter);

        feedItemDalFilter.setFeedId(feedItemEntityFilter.getFeedId());
        feedItemDalFilter.setUrl(feedItemEntityFilter.getUrl());
        feedItemDalFilter.setChecksum(feedItemEntityFilter.getChecksum());
        feedItemDalFilter.setApproved(feedItemEntityFilter.getApproved());
        feedItemDalFilter.setReposted(feedItemEntityFilter.getReposted());
        feedItemDalFilter.setViewed(feedItemEntityFilter.getViewed());
        feedItemDalFilter.setSent(feedItemEntityFilter.getReposted());
        feedItemDalFilter.setTagIds(feedItemEntityFilter.getTagIds());

        return feedItemDalFilter;
    }
}