package com.ubikz.scraper.core.app.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        List<String> str = new ArrayList<>();
        str.add("url = " + this.url);
        str.add("checksum = " + this.checksum);
        str.add("feed = " + this.feed);
        str.add("tags = " + this.tags);
        str.add("viewed = " + this.isViewed);
        str.add("approved = " + this.isApproved);
        str.add("reposted = " + this.isReposted);
        str.add("sent = " + this.isSent);

        return super.toString().concat(", " + str.stream().collect(Collectors.joining(", ")));
    }
}