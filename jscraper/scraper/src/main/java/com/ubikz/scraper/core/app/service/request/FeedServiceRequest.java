package com.ubikz.scraper.core.app.service.request;

public class FeedServiceRequest extends AbstractServiceRequest {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}