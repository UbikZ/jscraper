package com.ubikz.scraper.core.app.entity.helper;

import com.ubikz.scraper.core.app.dto.FeedDto;
import com.ubikz.scraper.core.app.dto.FeedTypeDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FeedEntityHelper extends AbstractEntityHelper {
    /**
     * @param data
     * @return
     */
    public static FeedDto getDtoFromDal(Map<String, Object> data) {
        FeedDto feedDto = (FeedDto) FeedEntityHelper.getBaseDtoFromDal(data, new FeedDto());

        if (data.containsKey("url")) {
            feedDto.setUrl((String) data.get("url"));
        }

        if (data.containsKey("feed_type_id")) {
            FeedTypeDto feedTypeDto = new FeedTypeDto();
            feedTypeDto.setId((int) data.get("feed_type_id"));
            feedDto.setFeedTypeDto(feedTypeDto);
        }

        return feedDto;
    }

    /**
     * @param dataList
     * @return
     */
    public static List<FeedDto> getDtoListFromDal(List<Map<String, Object>> dataList) {
        return dataList.stream().map(FeedEntityHelper::getDtoFromDal).collect(Collectors.toList());
    }
}