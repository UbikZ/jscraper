package com.ubikz.scraper.api.controller.request;

import java.util.List;

public class FeedItemRequestBody extends AbstractRequestBody {
    private Integer feedId;
    private String url;
    private String comment;
    private String checksum;
    private List<Integer> tagIds;
    private Boolean isViewed;
    private Boolean isApproved;
    private Boolean isReposted;
    private Boolean isSent;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getFeedId() {
        return feedId;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
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

    public List<Integer> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Integer> tagIds) {
        this.tagIds = tagIds;
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
        return super.toString() + "/ FeedItemRequestBody{" +
                "feedId=" + feedId +
                ", url='" + url + '\'' +
                ", comment='" + comment + '\'' +
                ", checksum='" + checksum + '\'' +
                ", tagIds=" + tagIds +
                ", isViewed=" + isViewed +
                ", isApproved=" + isApproved +
                ", isReposted=" + isReposted +
                ", isSent=" + isSent +
                '}';
    }
}