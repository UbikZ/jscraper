package com.ubikz.scraper.core.app.entity.request;

import java.util.List;

public class FeedItemListEntityRequest {
    private List<FeedItemEntityRequest> feedRequestList;

    public List<FeedItemEntityRequest> getFeedRequestList() {
        return feedRequestList;
    }

    public void setFeedRequestList(List<FeedItemEntityRequest> feedRequestList) {
        this.feedRequestList = feedRequestList;
    }
}