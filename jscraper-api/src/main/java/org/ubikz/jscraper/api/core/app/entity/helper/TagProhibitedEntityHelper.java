package org.ubikz.jscraper.api.core.app.entity.helper;

import org.ubikz.jscraper.api.core.app.dto.TagProhibitedDto;

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