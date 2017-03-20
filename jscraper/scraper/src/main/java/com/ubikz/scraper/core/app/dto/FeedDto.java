package com.ubikz.scraper.core.app.dto;

public class FeedDto extends AbstractDto {
    private String url;
    private FeedTypeDto feedTypeDto;

    public FeedDto() {
        super();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public FeedTypeDto getFeedTypeDto() {
        return feedTypeDto;
    }

    public void setFeedTypeDto(FeedTypeDto typeDto) {
        this.feedTypeDto = typeDto;
    }

    @Override
    public String toString() {
        return super.toString() + " / FeedDto{" +
                "url='" + url + '\'' +
                ", feedTypeDto=" + feedTypeDto +
                '}';
    }
}