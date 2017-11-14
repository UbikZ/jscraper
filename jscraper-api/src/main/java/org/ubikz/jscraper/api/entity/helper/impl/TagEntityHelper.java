package org.ubikz.jscraper.api.entity.helper.impl;

import org.ubikz.jscraper.api.dto.impl.TagDto;
import org.ubikz.jscraper.api.entity.helper.AbstractEntityHelper;

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