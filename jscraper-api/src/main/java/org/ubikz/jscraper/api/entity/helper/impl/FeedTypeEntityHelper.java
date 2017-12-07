package org.ubikz.jscraper.api.entity.helper.impl;

import org.ubikz.jscraper.api.dto.impl.FeedTypeDto;
import org.ubikz.jscraper.api.entity.helper.BaseEntityHelper;
import org.ubikz.jscraper.reference.table.field.FeedTypeReference;

import java.util.Map;

public class FeedTypeEntityHelper extends BaseEntityHelper<FeedTypeDto> {
    public FeedTypeDto getDtoFromDal(Map<String, Object> data) {
        FeedTypeDto feedTypeDto = super.getDtoFromDal(data);

        if (data.containsKey(FeedTypeReference.LABEL.get())) {
            feedTypeDto.setLabel((String) data.get(FeedTypeReference.LABEL.get()));
        }

        if (data.containsKey(FeedTypeReference.URL_REGEX.get())) {
            feedTypeDto.setUrlRegex((String) data.get(FeedTypeReference.URL_REGEX.get()));
        }

        if (data.containsKey(FeedTypeReference.CONTENT_REGEX.get())) {
            feedTypeDto.setContentRegex((String) data.get(FeedTypeReference.CONTENT_REGEX.get()));
        }

        return feedTypeDto;
    }
}