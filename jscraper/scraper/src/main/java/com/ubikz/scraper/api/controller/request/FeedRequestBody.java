package com.ubikz.scraper.api.controller.request;

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
        return super.toString() + "/ FeedRequestBody{" +
                "url='" + url + '\'' +
                ", feedTypeId=" + feedTypeId +
                '}';
    }
}