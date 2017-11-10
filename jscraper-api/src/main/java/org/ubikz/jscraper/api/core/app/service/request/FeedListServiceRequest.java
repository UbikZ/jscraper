package org.ubikz.jscraper.api.core.app.service.request;

import org.ubikz.jscraper.api.core.app.dto.FeedDto;

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