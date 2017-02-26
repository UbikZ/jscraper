package com.ubikz.scraper.api.controller.request;

public class FeedRequestBody extends AbstractRequestBody {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}