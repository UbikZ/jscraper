package org.ubikz.jscraper.api.entity.helper.impl;

import org.ubikz.jscraper.api.dto.impl.FeedDto;
import org.ubikz.jscraper.api.dto.impl.FeedItemDto;
import org.ubikz.jscraper.api.entity.helper.BaseEntityHelper;
import org.ubikz.jscraper.reference.table.field.FeedItemReference;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FeedItemEntityHelper extends BaseEntityHelper<FeedItemDto> {
    private TagEntityHelper tagEntityHelper;

    public FeedItemEntityHelper() {
        this.dto = new FeedItemDto();
        this.tagEntityHelper = new TagEntityHelper();
    }

    public FeedItemDto getDtoFromDal(Map<String, Object> data) {
        FeedItemDto feedItemDto = super.getDtoFromDal(data);

        if (data.containsKey(FeedItemReference.URL.get())) {
            feedItemDto.setUrl((String) data.get(FeedItemReference.URL.get()));
        }

        if (data.containsKey(FeedItemReference.COMMENT.get())) {
            feedItemDto.setComment((String) data.get(FeedItemReference.COMMENT.get()));
        }

        if (data.containsKey(FeedItemReference.CHECKSUM.get())) {
            feedItemDto.setChecksum((String) data.get(FeedItemReference.CHECKSUM.get()));
        }

        if (data.containsKey(FeedItemReference.FEED_ID.get())) {
            FeedDto feedDto = new FeedDto();
            feedDto.setId((int) data.get(FeedItemReference.FEED_ID.get()));
            feedItemDto.setFeed(feedDto);
        }

        if (data.containsKey(FeedItemReference.APPROVED.get())) {
            feedItemDto.setApproved((boolean) data.get(FeedItemReference.APPROVED.get()));
        }

        if (data.containsKey(FeedItemReference.REPOSTED.get())) {
            feedItemDto.setReposted((boolean) data.get(FeedItemReference.REPOSTED.get()));
        }

        if (data.containsKey(FeedItemReference.VIEWED.get())) {
            feedItemDto.setViewed((boolean) data.get(FeedItemReference.VIEWED.get()));
        }

        if (data.containsKey(FeedItemReference.SENT.get())) {
            feedItemDto.setSent((boolean) data.get(FeedItemReference.SENT.get()));
        }

        // Computed column from database
        if (data.containsKey(FeedItemReference.TAGS.get())) {
            String strTags = (String) data.get(FeedItemReference.TAGS.get());
            if (strTags != null) {
                feedItemDto.setTags(
                        Stream.of(strTags.split(",")).map(tagId ->
                                this.tagEntityHelper.getDtoFromDal(new HashMap<String, Object>() {{
                                    put("id", Integer.valueOf(tagId));
                                }})
                        ).collect(Collectors.toList())
                );
            }
        }

        return feedItemDto;
    }
}