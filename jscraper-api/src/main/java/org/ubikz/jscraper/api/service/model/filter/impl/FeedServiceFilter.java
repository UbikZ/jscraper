package org.ubikz.jscraper.api.service.model.filter.impl;

import org.ubikz.jscraper.api.service.model.filter.AbstractServiceFilter;

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