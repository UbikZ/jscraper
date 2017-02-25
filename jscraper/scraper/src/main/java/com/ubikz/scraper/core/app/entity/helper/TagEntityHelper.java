package com.ubikz.scraper.core.app.entity.helper;

import com.ubikz.scraper.core.app.dto.TagDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TagEntityHelper extends AbstractEntityHelper {
    /**
     * @param data
     * @return
     */
    public static TagDto getDtoFromDal(Map<String, Object> data) {
        return (TagDto) TagEntityHelper.getBaseDtoFromDal(data, new TagDto());
    }

    /**
     * @param dataList
     * @return
     */
    public static List<TagDto> getDtoListFromDal(List<Map<String, Object>> dataList) {
        return dataList.stream().map(TagEntityHelper::getDtoFromDal).collect(Collectors.toList());
    }
}