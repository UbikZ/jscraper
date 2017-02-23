package com.ubikz.scraper.core.app.dto;

import java.util.regex.Pattern;

public class FeedTypeDto extends AbstractDto {
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