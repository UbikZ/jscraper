package com.ubikz.scraper.core.app.entity.helper;

import com.ubikz.scraper.core.app.dto.FeedProhibitedDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FeedProhibitedEntityHelper extends AbstractEntityHelper {
    /**
     * @param data
     * @return
     */
    public static FeedProhibitedDto getDtoFromDal(Map<String, Object> data) {
        return (FeedProhibitedDto) FeedProhibitedEntityHelper.getBaseDtoFromDal(data, new FeedProhibitedDto());
    }

    /**
     * @param dataList
     * @return
     */
    public static List<FeedProhibitedDto> getDtoListFromDal(List<Map<String, Object>> dataList) {
        return dataList.stream().map(FeedProhibitedEntityHelper::getDtoFromDal).collect(Collectors.toList());
    }
}