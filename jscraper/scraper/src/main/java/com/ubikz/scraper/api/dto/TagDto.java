package com.ubikz.scraper.api.dto;

class TagDto extends AbstractDto {
    private String url;

    public TagDto() {
        super();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}