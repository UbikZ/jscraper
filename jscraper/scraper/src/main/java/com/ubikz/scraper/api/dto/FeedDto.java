package com.ubikz.scraper.api.dto;

class FeedDto extends AbstractDto {
    private String url;

    public FeedDto() {
        super();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}