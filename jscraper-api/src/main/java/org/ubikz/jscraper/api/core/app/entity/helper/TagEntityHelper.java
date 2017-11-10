package org.ubikz.jscraper.api.core.app.entity.helper;

import org.ubikz.jscraper.api.core.app.dto.TagDto;

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