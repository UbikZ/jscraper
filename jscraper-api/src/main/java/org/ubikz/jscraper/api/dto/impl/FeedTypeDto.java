package org.ubikz.jscraper.api.dto.impl;

import org.ubikz.jscraper.api.dto.BaseDto;

public class FeedTypeDto extends BaseDto {
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
        return "FeedTypeDto{"
                + "urlRegex='" + urlRegex + '\''
                + ", contentRegex='" + contentRegex + '\''
                + "} " + super.toString();
    }
}