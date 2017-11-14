package org.ubikz.jscraper.api.entity.helper.impl;

import org.ubikz.jscraper.api.dto.impl.TagProhibitedDto;
import org.ubikz.jscraper.api.entity.helper.AbstractEntityHelper;

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