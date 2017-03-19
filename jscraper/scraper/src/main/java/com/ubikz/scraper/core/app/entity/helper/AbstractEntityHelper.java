package com.ubikz.scraper.core.app.entity.helper;

import com.ubikz.scraper.core.app.dto.AbstractDto;
import io.spring.gradle.dependencymanagement.org.codehaus.plexus.interpolation.util.StringUtils;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.*;
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
     * @param attr
     * @return
     */
    final public List<AbstractDto> getDtoListFromReturnDal(List<Object> dataList, String attr) {
        List<AbstractDto> abstractDtoList = new ArrayList<>();

        for (Object element : dataList) {
            abstractDtoList.add(this.getDtoFromDal(new HashMap<String, Object>() {{
                put(attr, element);
            }}));
        }

        return abstractDtoList;
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
    public Map<Object, AbstractDto> getDtoMapFromDal(List<Map<String, Object>> dataList, String attr) throws Exception {
        Map<Object, AbstractDto> map = new HashMap<>();
        List<AbstractDto> list = this.getDtoListFromDal(dataList);

        for (AbstractDto abstractDto : list) {
            Method method = abstractDto
                    .getClass()
                    .getMethod("get" + StringUtils.capitalizeFirstLetter(attr));
            map.put(method.invoke(abstractDto), abstractDto);
        }

        return map;
    }

    /**
     * @param data
     * @return
     */
    abstract public AbstractDto getDtoFromDal(Map<String, Object> data);
}