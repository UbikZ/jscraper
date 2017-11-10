package org.ubikz.jscraper.api.core.app.entity.request;

import java.util.List;
import java.util.Set;

public class FeedItemListEntityRequest {
    private List<FeedItemEntityRequest> feedRequestList;
    private Set<String> tags;

    public List<FeedItemEntityRequest> getFeedRequestList() {
        return feedRequestList;
    }

    public void setFeedRequestList(List<FeedItemEntityRequest> feedRequestList) {
        this.feedRequestList = feedRequestList;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "FeedItemListEntityRequest{"
                + "feedRequestList=" + feedRequestList
                + ", tags=" + tags
                + '}';
    }
}