package com.ubikz.scraper.core.app.dal.request;

import java.util.List;

public class FeedItemDalRequest extends AbstractDalRequest {
    private Integer feedId;
    private Integer feedTypeId;
    private String url;
    private String checksum;
    private Boolean viewed;
    private Boolean approved;
    private Boolean reposted;
    private Boolean sent;

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