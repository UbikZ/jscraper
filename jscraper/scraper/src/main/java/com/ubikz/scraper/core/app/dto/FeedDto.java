package com.ubikz.scraper.core.app.dto;

public class FeedDto extends AbstractDto {
    private String url;
    private FeedTypeDto typeDto;

    public FeedDto() {
        super();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public FeedTypeDto getTypeDto() {
        return typeDto;
    }

    public void setTypeDto(FeedTypeDto typeDto) {
        this.typeDto = typeDto;
    }
}