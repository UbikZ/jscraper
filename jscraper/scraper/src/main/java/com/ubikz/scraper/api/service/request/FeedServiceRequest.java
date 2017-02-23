package com.ubikz.scraper.api.service.request;

public class FeedServiceRequest extends AbstractServiceRequest {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}