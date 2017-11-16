package org.ubikz.jscraper.api.entity.helper.impl;

import org.ubikz.jscraper.api.dto.impl.FeedDto;
import org.ubikz.jscraper.api.dto.impl.FeedTypeDto;
import org.ubikz.jscraper.api.entity.helper.BaseEntityHelper;

import java.util.Map;

public class FeedEntityHelper extends BaseEntityHelper {

    public static final String COLUMN_URL = "url";
    public static final String COLUMN_FEED_TYPE_ID = "feed_type_id";

    /**
     * @param data
     * @return
     */
    public FeedDto getDtoFromDal(Map<String, Object> data) {
        FeedDto feedDto = (FeedDto) this.getBaseDtoFromDal(data, new FeedDto());

        if (data.containsKey(COLUMN_URL)) {
            feedDto.setUrl((String) data.get(COLUMN_URL));
        }

        if (data.containsKey(COLUMN_FEED_TYPE_ID)) {
            FeedTypeDto feedTypeDto = new FeedTypeDto();
            feedTypeDto.setId((int) data.get(COLUMN_FEED_TYPE_ID));
            feedDto.setFeedTypeDto(feedTypeDto);
        }

        return feedDto;
    }
}