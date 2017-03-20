package com.ubikz.scraper.core.app.entity.filter;

import java.util.List;

public class FeedEntityFilter extends AbstractEntityFilter {
    private String url;
    private List<String> prohibitedTagList;
    private List<String> prohibitedFeedList;
    private String urlRegex;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getProhibitedTagList() {
        return prohibitedTagList;
    }

    public void setProhibitedTagList(List<String> prohibitedTagList) {
        this.prohibitedTagList = prohibitedTagList;
    }

    public List<String> getProhibitedFeedList() {
        return prohibitedFeedList;
    }

    public void setProhibitedFeedList(List<String> prohibitedFeedList) {
        this.prohibitedFeedList = prohibitedFeedList;
    }

    public String getUrlRegex() {
        return urlRegex;
    }

    public void setUrlRegex(String urlRegex) {
        this.urlRegex = urlRegex;
    }

    @Override
    public String toString() {
        return super.toString() + "/ FeedEntityFilter{" +
                "url='" + url + '\'' +
                ", prohibitedTagList=" + prohibitedTagList +
                ", prohibitedFeedList=" + prohibitedFeedList +
                ", urlRegex='" + urlRegex + '\'' +
                '}';
    }
}