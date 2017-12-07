package org.ubikz.jscraper.api.dal.model.request.impl;

import org.ubikz.jscraper.api.dal.model.request.BaseDalRequest;

public class FeedItemTagDalRequest extends BaseDalRequest {
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
        return "FeedItemTagDalRequest{"
                + "feedItemId=" + feedItemId
                + ", tagId=" + tagId
                + "} " + super.toString();
    }
}