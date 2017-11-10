package org.ubikz.jscraper.api.core.app.entity.helper;

import org.ubikz.jscraper.api.core.app.dto.FeedArticleDto;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class FeedArticleEntityHelper extends AbstractEntityHelper {

    public static final String COLUMN_URL = "url";
    public static final String COLUMN_LABEL = "label";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TAGS = "tags";
    public static final String COLUMN_PICTURES = "pictures";

    /**
     * @param data
     * @return
     */
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