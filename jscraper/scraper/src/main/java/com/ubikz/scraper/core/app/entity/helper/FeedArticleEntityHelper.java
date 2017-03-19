package com.ubikz.scraper.core.app.entity.helper;

import com.ubikz.scraper.core.app.dto.FeedArticleDto;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class FeedArticleEntityHelper extends AbstractEntityHelper {
    /**
     * @param data
     * @return
     */
    public FeedArticleDto getDtoFromDal(Map<String, Object> data) {
        FeedArticleDto dto = new FeedArticleDto();

        if (data.containsKey("url")) {
            dto.setUrl((String) data.get("url"));
        }

        if (data.containsKey("label")) {
            dto.setLabel((String) data.get("label"));
        }

        if (data.containsKey("author")) {
            dto.setAuthor((String) data.get("author"));
        }

        if (data.containsKey("date")) {
            dto.setDate((Date) data.get("date"));
        }

        if (data.containsKey("tags")) {
            dto.setTagList((List<String>) data.get("tags"));
        }

        if (data.containsKey("pictures")) {
            dto.setPictureList(((List<String>) data.get("pictures")));
        }

        return dto;
    }
}