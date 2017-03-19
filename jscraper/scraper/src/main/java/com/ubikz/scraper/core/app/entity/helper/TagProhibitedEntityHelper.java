package com.ubikz.scraper.core.app.entity.helper;

import com.ubikz.scraper.core.app.dto.TagProhibitedDto;

import java.util.Map;

public class TagProhibitedEntityHelper extends AbstractEntityHelper {
    /**
     * @param data
     * @return
     */
    public TagProhibitedDto getDtoFromDal(Map<String, Object> data) {
        return (TagProhibitedDto) this.getBaseDtoFromDal(data, new TagProhibitedDto());
    }
}