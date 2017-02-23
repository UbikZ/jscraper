package com.ubikz.scraper.core.app.dal.request;

public class FeedDalRequest extends AbstractDalRequest {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}