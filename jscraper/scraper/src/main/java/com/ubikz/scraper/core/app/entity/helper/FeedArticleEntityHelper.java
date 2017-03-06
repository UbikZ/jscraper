package com.ubikz.scraper.core.app.entity.helper;

import com.ubikz.scraper.core.app.dto.FeedArticleDto;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FeedArticleEntityHelper {
    /**
     * @param data
     * @return
     */
    public static FeedArticleDto getDtoFromDal(Map<String, Object> data) {
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

    /**
     * @param dataList
     * @return
     */
    public static List<FeedArticleDto> getDtoListFromDal(List<Map<String, Object>> dataList) {
        return dataList.stream().map(FeedArticleEntityHelper::getDtoFromDal).collect(Collectors.toList());
    }
}