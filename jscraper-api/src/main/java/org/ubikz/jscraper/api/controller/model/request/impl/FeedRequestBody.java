package org.ubikz.jscraper.api.controller.model.request.impl;

import org.ubikz.jscraper.api.controller.model.request.AbstractRequestBody;

public class FeedRequestBody extends AbstractRequestBody {
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
        return "FeedRequestBody{"
                + "url='" + url + '\''
                + ", feedTypeId=" + feedTypeId
                + "} " + super.toString();
    }
}