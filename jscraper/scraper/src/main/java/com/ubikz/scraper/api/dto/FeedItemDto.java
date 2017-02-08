package com.ubikz.scraper.api.dto;

class FeedItemDto extends AbstractDto {
    private FeedDto feed;
    private FeedTypeDto feedType;
    private String url;
    private String checksum;
    private boolean isEnabled;
    private boolean isViewed;
    private boolean isApproved;
    private boolean isReposted;
    private boolean isSent;

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

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
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

    public boolean isReposted() {
        return isReposted;
    }

    public void setReposted(boolean reposted) {
        isReposted = reposted;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }
}