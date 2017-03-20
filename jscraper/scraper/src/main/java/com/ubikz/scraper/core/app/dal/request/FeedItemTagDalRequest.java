package com.ubikz.scraper.core.app.dal.request;

public class FeedItemTagDalRequest extends AbstractDalRequest {
    private Integer feedItemId;
    private Integer tagId;

    public Integer getFeedItemId() {
        return feedItemId;
    }

    public void setFeedItemId(Integer feedItemId) {
        this.feedItemId = feedItemId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return super.toString() + "/ FeedItemTagDalRequest{" +
                "feedItemId=" + feedItemId +
                ", tagId=" + tagId +
                '}';
    }
}