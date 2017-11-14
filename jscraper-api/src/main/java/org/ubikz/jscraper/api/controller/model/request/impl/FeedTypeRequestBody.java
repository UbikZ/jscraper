package org.ubikz.jscraper.api.controller.model.request.impl;

import org.ubikz.jscraper.api.controller.model.request.AbstractRequestBody;

public class FeedTypeRequestBody extends AbstractRequestBody {
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
        return "FeedTypeRequestBody{"
                + "urlRegex='" + urlRegex + '\''
                + ", contentRegex='" + contentRegex + '\''
                + "} " + super.toString();
    }
}