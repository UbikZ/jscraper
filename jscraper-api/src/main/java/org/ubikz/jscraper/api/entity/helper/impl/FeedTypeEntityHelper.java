package org.ubikz.jscraper.api.entity.helper.impl;

import org.ubikz.jscraper.api.dto.impl.FeedTypeDto;
import org.ubikz.jscraper.api.entity.helper.BaseEntityHelper;

import java.util.Map;

public class FeedTypeEntityHelper extends BaseEntityHelper {

    public static final String COLUMN_URL_REGEX = "url_regex";
    public static final String COLUMN_CONTENT_REGEX = "content_regex";

    /**
     * @param data
     * @return
     */
    public FeedTypeDto getDtoFromDal(Map<String, Object> data) {
        FeedTypeDto feedTypeDto = (FeedTypeDto) this.getBaseDtoFromDal(data, new FeedTypeDto());

        if (data.containsKey(COLUMN_URL_REGEX)) {
            feedTypeDto.setUrlRegex((String) data.get(COLUMN_URL_REGEX));
        }

        if (data.containsKey(COLUMN_CONTENT_REGEX)) {
            feedTypeDto.setContentRegex((String) data.get(COLUMN_CONTENT_REGEX));
        }

        return feedTypeDto;
    }
}