package com.ubikz.scraper.core.app.entity.helper;

import com.ubikz.scraper.core.app.dto.TagProhibitedDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TagProhibitedEntityHelper extends AbstractEntityHelper {
    /**
     * @param data
     * @return
     */
    public static TagProhibitedDto getDtoFromDal(Map<String, Object> data) {
        return (TagProhibitedDto) TagProhibitedEntityHelper.getBaseDtoFromDal(data, new TagProhibitedDto());
    }

    /**
     * @param dataList
     * @return
     */
    public static List<TagProhibitedDto> getDtoListFromDal(List<Map<String, Object>> dataList) {
        return dataList.stream().map(TagProhibitedEntityHelper::getDtoFromDal).collect(Collectors.toList());
    }
}