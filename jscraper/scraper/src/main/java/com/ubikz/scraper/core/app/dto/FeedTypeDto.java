package com.ubikz.scraper.core.app.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FeedTypeDto extends AbstractDto {
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
        List<String> str = new ArrayList<>();
        str.add("urlRegex = " + this.urlRegex);
        str.add("contentRegex = " + this.contentRegex);

        return super.toString().concat(", " + str.stream().collect(Collectors.joining(", ")));
    }
}