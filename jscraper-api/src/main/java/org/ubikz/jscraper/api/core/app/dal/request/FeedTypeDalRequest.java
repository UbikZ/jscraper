package org.ubikz.jscraper.api.core.app.dal.request;

public class FeedTypeDalRequest extends AbstractDalRequest {
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