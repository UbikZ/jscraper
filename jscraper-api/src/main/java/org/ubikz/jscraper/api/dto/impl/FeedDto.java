package org.ubikz.jscraper.api.dto.impl;

import org.ubikz.jscraper.api.dto.BaseDto;

public class FeedDto extends BaseDto {
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
        return "FeedDto{"
                + "url='" + url + '\''
                + ", feedTypeDto=" + feedTypeDto
                + "} " + super.toString();
    }
}