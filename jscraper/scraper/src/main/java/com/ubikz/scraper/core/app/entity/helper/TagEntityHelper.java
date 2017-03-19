package com.ubikz.scraper.core.app.entity.helper;

import com.ubikz.scraper.core.app.dto.TagDto;

import java.util.Map;

public class TagEntityHelper extends AbstractEntityHelper {
    /**
     * @param data
     * @return
     */
    public TagDto getDtoFromDal(Map<String, Object> data) {
        return (TagDto) this.getBaseDtoFromDal(data, new TagDto());
    }
}