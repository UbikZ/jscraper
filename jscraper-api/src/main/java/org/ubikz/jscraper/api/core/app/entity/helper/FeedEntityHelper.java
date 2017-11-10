package org.ubikz.jscraper.api.core.app.entity.helper;

import org.ubikz.jscraper.api.core.app.dto.FeedDto;
import org.ubikz.jscraper.api.core.app.dto.FeedTypeDto;

import java.util.Map;

public class FeedEntityHelper extends AbstractEntityHelper {

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