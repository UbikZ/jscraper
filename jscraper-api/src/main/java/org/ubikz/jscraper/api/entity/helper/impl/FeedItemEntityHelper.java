package org.ubikz.jscraper.api.entity.helper.impl;

import org.ubikz.jscraper.api.dto.impl.FeedDto;
import org.ubikz.jscraper.api.dto.impl.FeedItemDto;
import org.ubikz.jscraper.api.entity.helper.AbstractEntityHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FeedItemEntityHelper extends AbstractEntityHelper {
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_COMMENT = "comment";
    public static final String COLUMN_CHECKSUM = "checksum";
    public static final String COLUMN_FEED_ID = "feed_id";
    public static final String COLUMN_APPROVED = "approved";
    public static final String COLUMN_REPOSTED = "reposted";
    public static final String COLUMN_VIEWED = "viewed";
    public static final String COLUMN_SENT = "sent";
    public static final String COLUMN_TAGS = "tags";
    private TagEntityHelper tagEntityHelper;

    public FeedItemEntityHelper() {
        this.tagEntityHelper = new TagEntityHelper();
    }

    /**
     * @param data
     * @return
     */
    public FeedItemDto getDtoFromDal(Map<String, Object> data) {
        FeedItemDto feedItemDto = (FeedItemDto) this.getBaseDtoFromDal(data, new FeedItemDto());

        if (data.containsKey(COLUMN_URL)) {
            feedItemDto.setUrl((String) data.get(COLUMN_URL));
        }

        if (data.containsKey(COLUMN_COMMENT)) {
            feedItemDto.setComment((String) data.get(COLUMN_COMMENT));
        }

        if (data.containsKey(COLUMN_CHECKSUM)) {
            feedItemDto.setChecksum((String) data.get(COLUMN_CHECKSUM));
        }

        if (data.containsKey(COLUMN_FEED_ID)) {
            FeedDto feedDto = new FeedDto();
            feedDto.setId((int) data.get(COLUMN_FEED_ID));
            feedItemDto.setFeed(feedDto);
        }

        if (data.containsKey(COLUMN_APPROVED)) {
            feedItemDto.setApproved((boolean) data.get(COLUMN_APPROVED));
        }

        if (data.containsKey(COLUMN_REPOSTED)) {
            feedItemDto.setReposted((boolean) data.get(COLUMN_REPOSTED));
        }

        if (data.containsKey(COLUMN_VIEWED)) {
            feedItemDto.setViewed((boolean) data.get(COLUMN_VIEWED));
        }

        if (data.containsKey(COLUMN_SENT)) {
            feedItemDto.setSent((boolean) data.get(COLUMN_SENT));
        }

        // Computed column from database
        if (data.containsKey(COLUMN_TAGS)) {
            String strTags = (String) data.get(COLUMN_TAGS);
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