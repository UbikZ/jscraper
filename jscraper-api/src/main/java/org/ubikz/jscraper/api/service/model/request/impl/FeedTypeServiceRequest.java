package org.ubikz.jscraper.api.service.model.request.impl;

import org.ubikz.jscraper.api.service.model.request.BaseServiceRequest;

public class FeedTypeServiceRequest extends BaseServiceRequest {
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
        return "FeedTypeServiceRequest{"
                + "urlRegex='" + urlRegex + '\''
                + ", contentRegex='" + contentRegex + '\''
                + "} " + super.toString();
    }
}