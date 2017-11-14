package org.ubikz.jscraper.api.entity.model.request.impl;

import org.ubikz.jscraper.api.entity.model.request.AbstractEntityRequest;

public class FeedEntityRequest extends AbstractEntityRequest {
    private String url;
    private Integer feedTypeId;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getFeedTypeId() {
        return feedTypeId;
    }

    public void setFeedTypeId(Integer feedTypeId) {
        this.feedTypeId = feedTypeId;
    }

    @Override
    public String toString() {
        return "FeedEntityRequest{"
                + "url='" + url + '\''
                + ", feedTypeId=" + feedTypeId
                + "} " + super.toString();
    }
}