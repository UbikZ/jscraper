package com.ubikz.scraper.core.app.entity.helper;

import com.ubikz.scraper.core.app.dto.AbstractDto;
import com.ubikz.scraper.core.app.dto.FeedTypeDto;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

abstract public class AbstractEntityHelper {
    /**
     * @param data
     * @param dto
     * @return
     */
    public AbstractDto getBaseDtoFromDal(Map<String, Object> data, AbstractDto dto) {
        if (data.containsKey("id")) {
            dto.setId((int) data.get("id"));
        }

        if (data.containsKey("date")) {
            Object date = data.get("date");
            if (date != null) {
                Timestamp timestamp = (Timestamp) date;
                long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);
                dto.setDate(new Date(milliseconds));
            }
        }

        if (data.containsKey("label")) {
            dto.setLabel((String) data.get("label"));
        }

        if (data.containsKey("enabled")) {
            dto.setEnabled((Boolean) data.get("enabled"));
        }

        return dto;
    }

    /**
     * @param dataList
     * @return
     */
    final public List<AbstractDto> getDtoListFromDal(List<Map<String, Object>> dataList) {
        return dataList.stream().map(this::getDtoFromDal).collect(Collectors.toList());
    }

    /**
     * @param dataList
     * @return
     */
    public Map<Integer, AbstractDto> getDtoMapFromDal(List<Map<String, Object>> dataList) {
        Map<Integer, AbstractDto> map = new HashMap<>();
        List<AbstractDto> list = this.getDtoListFromDal(dataList);

        for (AbstractDto abstractDto : list) {
            map.put(abstractDto.getId(), abstractDto);
        }

        return map;
    }

    /**
     * @param data
     * @return
     */
    abstract public AbstractDto getDtoFromDal(Map<String, Object> data);
}