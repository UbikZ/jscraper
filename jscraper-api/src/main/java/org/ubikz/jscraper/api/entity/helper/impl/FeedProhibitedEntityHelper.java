package org.ubikz.jscraper.api.entity.helper.impl;

import org.ubikz.jscraper.api.dto.impl.FeedProhibitedDto;
import org.ubikz.jscraper.api.entity.helper.AbstractEntityHelper;

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