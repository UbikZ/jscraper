package org.ubikz.jscraper.api.core.app.service.filter;

public class FeedServiceFilter extends AbstractServiceFilter {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "FeedServiceFilter{"
                + "url='" + url + '\''
                + "} " + super.toString();
    }
}