package org.ubikz.jscraper.api.entity.helper.impl;

import org.ubikz.jscraper.api.dto.impl.TagDto;
import org.ubikz.jscraper.api.entity.helper.BaseEntityHelper;

import java.util.Map;

public class TagEntityHelper extends BaseEntityHelper {
    /**
     * @param data
     * @return
     */
    public TagDto getDtoFromDal(Map<String, Object> data) {
        return (TagDto) this.getBaseDtoFromDal(data, new TagDto());
    }
}