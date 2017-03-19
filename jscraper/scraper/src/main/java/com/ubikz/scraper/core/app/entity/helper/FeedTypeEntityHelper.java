package com.ubikz.scraper.core.app.entity.helper;

import com.ubikz.scraper.core.app.dto.FeedTypeDto;

import java.util.Map;

public class FeedTypeEntityHelper extends AbstractEntityHelper {
    /**
     * @param data
     * @return
     */
    public FeedTypeDto getDtoFromDal(Map<String, Object> data) {
        FeedTypeDto feedTypeDto = (FeedTypeDto) this.getBaseDtoFromDal(data, new FeedTypeDto());

        if (data.containsKey("url_regex")) {
            feedTypeDto.setUrlRegex((String) data.get("url_regex"));
        }

        if (data.containsKey("content_regex")) {
            feedTypeDto.setContentRegex((String) data.get("content_regex"));
        }

        return feedTypeDto;
    }
}