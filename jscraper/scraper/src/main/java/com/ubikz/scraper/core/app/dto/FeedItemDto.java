package com.ubikz.scraper.core.app.dto;

import java.util.List;

public class FeedItemDto extends AbstractDto {
    private FeedDto feed;
    private String url;
    private String checksum;
    private List<TagDto> tags;
    private Boolean isViewed;
    private Boolean isApproved;
    private Boolean isReposted;
    private Boolean isSent;

    public FeedDto getFeed() {
        return feed;
    }

    public void setFeed(FeedDto feed) {
        this.feed = feed;
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
        return isViewed;
    }

    public void setViewed(Boolean viewed) {
        isViewed = viewed;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public Boolean getReposted() {
        return isReposted;
    }

    public void setReposted(Boolean reposted) {
        isReposted = reposted;
    }

    public Boolean getSent() {
        return isSent;
    }

    public void setSent(Boolean sent) {
        isSent = sent;
    }

    @Override
    public String toString() {
        return super.toString() + " / FeedItemDto{" +
                "feed=" + feed +
                ", url='" + url + '\'' +
                ", checksum='" + checksum + '\'' +
                ", tags=" + tags +
                ", isViewed=" + isViewed +
                ", isApproved=" + isApproved +
                ", isReposted=" + isReposted +
                ", isSent=" + isSent +
                '}';
    }
}