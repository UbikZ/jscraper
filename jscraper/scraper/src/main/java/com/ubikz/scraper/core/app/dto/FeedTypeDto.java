package com.ubikz.scraper.core.app.dto;

public class FeedTypeDto extends AbstractDto {
    private String urlRegex;
    private String contentRegex;

    public String getUrlRegex() {
        return urlRegex;
    }

    public void setUrlRegex(String urlRegex) {
        this.urlRegex = urlRegex;
    }

    public String getContentRegex() {
        return contentRegex;
    }

    public void setContentRegex(String contentRegex) {
        this.contentRegex = contentRegex;
    }

    @Override
    public String toString() {
        return super.toString() + " / FeedTypeDto{" +
                "urlRegex='" + urlRegex + '\'' +
                ", contentRegex='" + contentRegex + '\'' +
                '}';
    }
}