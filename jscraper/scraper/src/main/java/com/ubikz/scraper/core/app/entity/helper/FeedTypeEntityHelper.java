package com.ubikz.scraper.core.app.entity.helper;

import com.ubikz.scraper.core.app.dto.FeedTypeDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FeedTypeEntityHelper extends AbstractEntityHelper {
    /**
     * @param data
     * @return
     */
    public static FeedTypeDto getDtoFromDal(Map<String, Object> data) {
        FeedTypeDto feedTypeDto = (FeedTypeDto) FeedTypeEntityHelper.getBaseDtoFromDal(data, new FeedTypeDto());

        if (data.containsKey("url_regex")) {
            feedTypeDto.setUrlRegex((String) data.get("url_regex"));
        }

        if (data.containsKey("content_regex")) {
            feedTypeDto.setContentRegex((String) data.get("content_regex"));
        }

        return feedTypeDto;
    }

    /**
     * @param dataList
     * @return
     */
    public static List<FeedTypeDto> getDtoListFromDal(List<Map<String, Object>> dataList) {
        return dataList.stream().map(FeedTypeEntityHelper::getDtoFromDal).collect(Collectors.toList());
    }
}