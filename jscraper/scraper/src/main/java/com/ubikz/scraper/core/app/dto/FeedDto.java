package com.ubikz.scraper.core.app.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        List<String> str = new ArrayList<>();
        str.add("url = " + this.url);
        str.add("typeDto = " + this.feedTypeDto);

        return super.toString().concat(", " + str.stream().collect(Collectors.joining(", ")));
    }
}