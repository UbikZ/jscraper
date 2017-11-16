package org.ubikz.jscraper.api.service.model.request.impl;

import org.ubikz.jscraper.api.service.model.request.BaseServiceRequest;

public class FeedServiceRequest extends BaseServiceRequest {
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
        return "FeedServiceRequest{"
                + "url='" + url + '\''
                + ", feedTypeId=" + feedTypeId
                + "} " + super.toString();
    }
}