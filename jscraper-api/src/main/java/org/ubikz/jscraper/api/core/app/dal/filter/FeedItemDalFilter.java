package org.ubikz.jscraper.api.core.app.dal.filter;

import java.util.List;

public class FeedItemDalFilter extends AbstractDalFilter {
    private Integer feedId;
    private String url;
    private String checksum;
    private List<String> tagNames;
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
        return "FeedItemDalFilter{"
                + "feedId=" + feedId
                + ", url='" + url + '\''
                + ", checksum='" + checksum + '\''
                + ", tagNames=" + tagNames
                + ", viewed=" + viewed
                + ", approved=" + approved
                + ", reposted=" + reposted
                + ", sent=" + sent
                + "} " + super.toString();
    }
}