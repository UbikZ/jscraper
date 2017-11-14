package org.ubikz.jscraper.api.service.model.request.impl;

import org.ubikz.jscraper.api.dto.impl.FeedDto;

import java.util.ArrayList;
import java.util.List;

public class FeedListServiceRequest {
    private List<FeedDto> feedList = new ArrayList<>();

    public List<FeedDto> getFeedList() {
        return feedList;
    }

    public void setFeedList(List<FeedDto> feedList) {
        this.feedList = feedList;
    }

    @Override
    public String toString() {
        return "FeedListServiceRequest{"
                + "feedList=" + feedList
                + '}';
    }
}