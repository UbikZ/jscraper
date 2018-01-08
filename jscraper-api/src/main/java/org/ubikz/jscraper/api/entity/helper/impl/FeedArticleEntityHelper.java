package org.ubikz.jscraper.api.entity.helper.impl;

import org.ubikz.jscraper.api.dto.impl.FeedArticleDto;
import org.ubikz.jscraper.api.entity.helper.BaseEntityHelper;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class FeedArticleEntityHelper extends BaseEntityHelper<FeedArticleDto> {
    private static final String COLUMN_URL = "url";
    private static final String COLUMN_LABEL = "label";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TAGS = "tags";
    private static final String COLUMN_PICTURES = "pictures";

    @SuppressWarnings("unchecked")
    public FeedArticleDto getDtoFromDal(Map<String, Object> data) {
        FeedArticleDto dto = new FeedArticleDto();

        if (data.containsKey(COLUMN_URL)) {
            dto.setUrl((String) data.get(COLUMN_URL));
        }

        if (data.containsKey(COLUMN_LABEL)) {
            dto.setLabel((String) data.get(COLUMN_LABEL));
        }

        if (data.containsKey(COLUMN_AUTHOR)) {
            dto.setAuthor((String) data.get(COLUMN_AUTHOR));
        }

        if (data.containsKey(COLUMN_DATE)) {
            dto.setDate((Date) data.get(COLUMN_DATE));
        }

        if (data.containsKey(COLUMN_TAGS)) {
            dto.setTagList((List<String>) data.get(COLUMN_TAGS));
        }

        if (data.containsKey(COLUMN_PICTURES)) {
            dto.setPictureList(((List<String>) data.get(COLUMN_PICTURES)));
        }

        return dto;
    }
}
