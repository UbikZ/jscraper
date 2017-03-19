package com.ubikz.scraper.core.app.entity.helper;

import com.ubikz.scraper.core.app.dto.FeedProhibitedDto;

import java.util.Map;

public class FeedProhibitedEntityHelper extends AbstractEntityHelper {
    /**
     * @param data
     * @return
     */
    public FeedProhibitedDto getDtoFromDal(Map<String, Object> data) {
        return (FeedProhibitedDto) this.getBaseDtoFromDal(data, new FeedProhibitedDto());
    }
}