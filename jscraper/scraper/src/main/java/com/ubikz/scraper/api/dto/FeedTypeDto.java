package com.ubikz.scraper.api.dto;

import java.util.regex.Pattern;

class FeedTypeDto extends AbstractDto {
    private Pattern urlRegex;
    private Pattern contentRegex;

    public Pattern getUrlRegex() {
        return urlRegex;
    }

    public void setUrlRegex(Pattern urlRegex) {
        this.urlRegex = urlRegex;
    }

    public Pattern getContentRegex() {
        return contentRegex;
    }

    public void setContentRegex(Pattern contentRegex) {
        this.contentRegex = contentRegex;
    }
}