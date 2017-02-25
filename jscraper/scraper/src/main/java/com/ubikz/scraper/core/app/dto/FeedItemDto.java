package com.ubikz.scraper.core.app.dto;

import java.util.List;

public class FeedItemDto extends AbstractDto {
    private FeedDto feed;
    private FeedTypeDto feedType;
    private String url;
    private String checksum;
    private List<TagDto> tags;
    private Boolean viewed;
    private Boolean approved;
    private Boolean reposted;
    private Boolean sent;

    public FeedDto getFeed() {
        return feed;
    }

    public void setFeed(FeedDto feed) {
        this.feed = feed;
    }

    public FeedTypeDto getFeedType() {
        return feedType;
    }

    public void setFeedType(FeedTypeDto feedType) {
        this.feedType = feedType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public List<TagDto> getTags() {
        return tags;
    }

    public void setTags(List<TagDto> tags) {
        this.tags = tags;
    }

    public Boolean getViewed() {
        return viewed;
    }

    public void setViewed(Boolean viewed) {
        this.viewed = viewed;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Boolean getReposted() {
        return reposted;
    }

    public void setReposted(Boolean reposted) {
        this.reposted = reposted;
    }

    public Boolean getSent() {
        return sent;
    }

    public void setSent(Boolean sent) {
        this.sent = sent;
    }
}