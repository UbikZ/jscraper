package com.ubikz.scraper.api.controller.filter;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FeedItemFilterBody extends AbstractFilterBody {
    @JsonProperty("feed")
    private Integer feedId;
    @JsonProperty("feedType")
    private Integer feedTypeId;
    @JsonProperty("url")
    private String url;
    @JsonProperty("checksum")
    private String checksum;
    @JsonProperty("tags")
    private List<String> tagNames;
    @JsonProperty("viewed")
    private Boolean isViewed;
    @JsonProperty("approved")
    private Boolean isApproved;
    @JsonProperty("reposted")
    private Boolean isReposted;
    @JsonProperty("sent")
    private Boolean isSent;

    public Integer getFeedId() {
        return feedId;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }

    public Integer getFeedTypeId() {
        return feedTypeId;
    }

    public void setFeedTypeId(Integer feedTypeId) {
        this.feedTypeId = feedTypeId;
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

    public List<String> getTagNames() {
        return tagNames;
    }

    public void setTagNames(List<String> tagNames) {
        this.tagNames = tagNames;
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
}