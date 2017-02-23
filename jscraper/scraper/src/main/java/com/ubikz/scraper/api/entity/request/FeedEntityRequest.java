package com.ubikz.scraper.api.entity.request;

public class FeedEntityRequest extends AbstractEntityRequest {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}