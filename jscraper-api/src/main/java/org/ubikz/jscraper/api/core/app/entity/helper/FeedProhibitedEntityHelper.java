package org.ubikz.jscraper.api.core.app.entity.helper;

import org.ubikz.jscraper.api.core.app.dto.FeedProhibitedDto;

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