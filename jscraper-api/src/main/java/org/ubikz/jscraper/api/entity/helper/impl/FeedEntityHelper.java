package org.ubikz.jscraper.api.entity.helper.impl;

import org.ubikz.jscraper.api.dto.impl.FeedDto;
import org.ubikz.jscraper.api.dto.impl.FeedTypeDto;
import org.ubikz.jscraper.api.entity.helper.BaseEntityHelper;
import org.ubikz.jscraper.reference.table.field.FeedReference;

import java.util.Map;

public class FeedEntityHelper extends BaseEntityHelper<FeedDto> {
    public FeedEntityHelper() {
        this.dto = new FeedDto();
    }

    public FeedDto getDtoFromDal(Map<String, Object> data) {
        FeedDto feedDto = super.getDtoFromDal(data);

        if (data.containsKey(FeedReference.URL.get())) {
            feedDto.setUrl((String) data.get(FeedReference.URL.get()));
        }

        if (data.containsKey(FeedReference.LABEL.get())) {
            feedDto.setLabel((String) data.get(FeedReference.LABEL.get()));
        }

        if (data.containsKey(FeedReference.FEED_TYPE_ID.get())) {
            FeedTypeDto feedTypeDto = new FeedTypeDto();
            feedTypeDto.setId((int) data.get(FeedReference.FEED_TYPE_ID.get()));
            feedDto.setFeedTypeDto(feedTypeDto);
        }

        return feedDto;
    }
}