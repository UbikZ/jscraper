package com.ubikz.scraper.api.dto;

import java.util.regex.Pattern;

class FeedTypeDto extends AbstractDto {
    private Pattern feedTypeRegex;
    private Pattern contentRegex;

    public Pattern getFeedTypeRegex() {
        return feedTypeRegex;
    }

    public void setFeedTypeRegex(Pattern feedTypeRegex) {
        this.feedTypeRegex = feedTypeRegex;
    }

    public Pattern getContentRegex() {
        return contentRegex;
    }

    public void setContentRegex(Pattern contentRegex) {
        this.contentRegex = contentRegex;
    }
}