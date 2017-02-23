package com.ubikz.scraper.core.app.entity.helper;

import com.ubikz.scraper.core.app.dto.FeedDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FeedEntityHelper extends AbstractEntityHelper {
    public static FeedDto getDtoFromDal(Map<String, Object> data) {
        FeedDto feedDto = new FeedDto();

        if (data.containsKey("id")) {
            feedDto.setId((int) data.get("id"));
        }

        if (data.containsKey("url")) {
            feedDto.setUrl((String) data.get("url"));
        }

        if (data.containsKey("label")) {
            feedDto.setLabel((String) data.get("label"));
        }

        if (data.containsKey("enabled")) {
            feedDto.setEnabled((boolean) data.get("enabled"));
        }

        return feedDto;
    }

    public static List<FeedDto> getDtoListFromDal(List<Map<String, Object>> dataList) {
        return dataList.stream().map(FeedEntityHelper::getDtoFromDal).collect(Collectors.toList());
    }
}