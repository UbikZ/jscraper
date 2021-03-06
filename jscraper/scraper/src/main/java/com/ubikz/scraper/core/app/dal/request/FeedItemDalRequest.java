package com.ubikz.scraper.core.app.dal.request;

public class FeedItemDalRequest extends AbstractDalRequest {
    private Integer feedId;
    private String url;
    private String comment;
    private String checksum;
    private Boolean viewed;
    private Boolean approved;
    private Boolean reposted;
    private Boolean sent;

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

    @Override
    public String toString() {
        return super.toString() + "/ FeedItemDalRequest{" +
                "feedId=" + feedId +
                ", url='" + url + '\'' +
                ", checksum='" + checksum + '\'' +
                ", viewed=" + viewed +
                ", approved=" + approved +
                ", reposted=" + reposted +
                ", sent=" + sent +
                '}';
    }
}