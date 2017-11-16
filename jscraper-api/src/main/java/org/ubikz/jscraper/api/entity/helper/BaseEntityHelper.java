package org.ubikz.jscraper.api.entity.helper;

import org.ubikz.jscraper.api.dto.BaseDto;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseEntityHelper {

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_LABEL = "label";
    public static final String COLUMN_ENABLED = "enabled";

    /**
     * @param data
     * @param dto
     * @return
     */
    public BaseDto getBaseDtoFromDal(Map<String, Object> data, BaseDto dto) {
        if (data.containsKey(COLUMN_ID)) {
            dto.setId((int) data.get(COLUMN_ID));
        }

        if (data.containsKey(COLUMN_DATE)) {
            Object date = data.get(COLUMN_DATE);
            if (date != null) {
                Timestamp timestamp = (Timestamp) date;
                long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);
                dto.setDate(new Date(milliseconds));
            }
        }

        if (data.containsKey(COLUMN_LABEL)) {
            dto.setLabel((String) data.get(COLUMN_LABEL));
        }

        if (data.containsKey(COLUMN_ENABLED)) {
            dto.setEnabled((Boolean) data.get(COLUMN_ENABLED));
        }

        return dto;
    }

    /**
     * @param dataList
     * @param attr
     * @return
     */
    public final List<BaseDto> getDtoListFromReturnDal(List<Object> dataList, String attr) {
        List<BaseDto> baseDtoList = new ArrayList<>();

        for (Object element : dataList) {
            baseDtoList.add(this.getDtoFromDal(new HashMap<String, Object>() {{
                put(attr, element);
            }}));
        }

        return baseDtoList;
    }

    /**
     * @param dataList
     * @return
     */
    public final List<BaseDto> getDtoListFromDal(List<Map<String, Object>> dataList) {
        return dataList.stream().map(this::getDtoFromDal).collect(Collectors.toList());
    }

    /**
     * @param dataList
     * @return
     */
    public Map<Object, BaseDto> getDtoMapFromDal(List<Map<String, Object>> dataList, String attr) throws Exception {
        Map<Object, BaseDto> map = new HashMap<>();
        List<BaseDto> list = this.getDtoListFromDal(dataList);

        for (BaseDto baseDto : list) {
            Method method = baseDto
                    .getClass()
                    .getMethod("get" + attr.substring(0, 1).toUpperCase() + attr.substring(1));
            map.put(method.invoke(baseDto), baseDto);
        }

        return map;
    }

    /**
     * @param data
     * @return
     */
    public abstract BaseDto getDtoFromDal(Map<String, Object> data);
}