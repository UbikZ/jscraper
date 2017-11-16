package org.ubikz.jscraper.api.service.model.filter.impl;

import org.ubikz.jscraper.api.service.model.filter.BaseServiceFilter;

public class FeedServiceFilter extends BaseServiceFilter {
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