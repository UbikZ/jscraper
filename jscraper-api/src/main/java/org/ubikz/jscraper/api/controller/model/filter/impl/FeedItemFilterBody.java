package org.ubikz.jscraper.api.controller.model.filter.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.ubikz.jscraper.api.controller.model.filter.AbstractFilterBody;

import java.util.Arrays;

public class FeedItemFilterBody extends AbstractFilterBody {
    private Integer feedId = null;
    private Integer feedTypeId = null;
    private String url = null;
    private String checksum = null;
    private String[] tags = {};
    private Boolean isViewed = null;
    private Boolean isApproved = null;
    private Boolean isReposted = null;
    private Boolean isSent = null;

    public Integer getFeedId() {
        return feedId;
    }

    @JsonProperty("feedId")
    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }

    public Integer getFeedTypeId() {
        return feedTypeId;
    }

    @JsonProperty("feedTypeId")
    public void setFeedTypeId(Integer feedTypeId) {
        this.feedTypeId = feedTypeId;
    }

    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    public String getChecksum() {
        return checksum;
    }

    @JsonProperty("checksum")
    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String[] getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public Boolean getViewed() {
        return isViewed;
    }

    @JsonProperty("viewed")
    public void setViewed(Boolean viewed) {
        isViewed = viewed;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    @JsonProperty("approved")
    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public Boolean getReposted() {
        return isReposted;
    }

    @JsonProperty("reposted")
    public void setReposted(Boolean reposted) {
        isReposted = reposted;
    }

    public Boolean getSent() {
        return isSent;
    }

    @JsonProperty("sent")
    public void setSent(Boolean sent) {
        isSent = sent;
    }

    @Override
    public String toString() {
        return "FeedItemFilterBody{"
                + "feedId=" + feedId
                + ", feedTypeId=" + feedTypeId
                + ", url='" + url + '\''
                + ", checksum='" + checksum + '\''
                + ", tags=" + Arrays.toString(tags)
                + ", isViewed=" + isViewed
                + ", isApproved=" + isApproved
                + ", isReposted=" + isReposted
                + ", isSent=" + isSent
                + "} " + super.toString();
    }
}