package org.ubikz.jscraper.api.dal.model.request.impl;

import org.ubikz.jscraper.api.dal.model.request.BaseDalRequest;

public class FeedTypeDalRequest extends BaseDalRequest {
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
        return "FeedTypeDalRequest{"
                + "urlRegex='" + urlRegex + '\''
                + ", contentRegex='" + contentRegex + '\''
                + "} " + super.toString();
    }
}