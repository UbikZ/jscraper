package com.ubikz.scraper.core.app.entity.helper;

import com.ubikz.scraper.core.app.dto.FeedDto;
import com.ubikz.scraper.core.app.dto.FeedItemDto;
import com.ubikz.scraper.core.app.dto.TagDto;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FeedItemEntityHelper extends AbstractEntityHelper {
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

        if (data.containsKey("url")) {
            feedItemDto.setUrl((String) data.get("url"));
        }

        if (data.containsKey("comment")) {
            feedItemDto.setUrl((String) data.get("comment"));
        }

        if (data.containsKey("checksum")) {
            feedItemDto.setChecksum((String) data.get("checksum"));
        }

        if (data.containsKey("feed_id")) {
            FeedDto feedDto = new FeedDto();
            feedDto.setId((int) data.get("feed_id"));
            feedItemDto.setFeed(feedDto);
        }

        if (data.containsKey("approved")) {
            feedItemDto.setApproved((boolean) data.get("approved"));
        }

        if (data.containsKey("reposted")) {
            feedItemDto.setReposted((boolean) data.get("reposted"));
        }

        if (data.containsKey("viewed")) {
            feedItemDto.setViewed((boolean) data.get("viewed"));
        }

        if (data.containsKey("sent")) {
            feedItemDto.setSent((boolean) data.get("sent"));
        }

        // Computed column from database
        if (data.containsKey("tags")) {
            String strTags = (String) data.get("tags");
            if (strTags != null) {
                feedItemDto.setTags(
                        Stream.of(strTags.split(",")).map(tagId ->
                                (TagDto) this.tagEntityHelper.getDtoFromDal(new HashMap<String, Object>() {{
                                    put("id", Integer.valueOf(tagId));
                                }})
                        ).collect(Collectors.toList())
                );
            }
        }

        return feedItemDto;
    }
}