package com.ubikz.scraper.core.app.dal.request;

public class FeedDalRequest extends AbstractDalRequest {
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
        return super.toString() + "/ FeedDalRequest{" +
                "url='" + url + '\'' +
                ", feedTypeId=" + feedTypeId +
                '}';
    }
}