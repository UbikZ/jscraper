package com.ubikz.scraper.core.app.dto;

import java.util.List;

public class FeedItemDto extends AbstractDto {
    private FeedDto feed;
    private FeedTypeDto feedType;
    private String url;
    private String checksum;
    private boolean isViewed;
    private boolean isApproved;
    private boolean isRepposted;
    private boolean isSent;
    private List<TagDto> tags;

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

    public boolean isViewed() {
        return isViewed;
    }

    public void setViewed(boolean viewed) {
        isViewed = viewed;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public boolean isRepposted() {
        return isRepposted;
    }

    public void setRepposted(boolean repposted) {
        isRepposted = repposted;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    public List<TagDto> getTags() {
        return tags;
    }

    public void setTags(List<TagDto> tags) {
        this.tags = tags;
    }
}