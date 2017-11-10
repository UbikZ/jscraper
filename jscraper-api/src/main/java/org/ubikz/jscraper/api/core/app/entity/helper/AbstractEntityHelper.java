package org.ubikz.jscraper.api.core.app.entity.helper;

import org.ubikz.jscraper.api.core.app.dto.AbstractDto;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractEntityHelper {

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_LABEL = "label";
    public static final String COLUMN_ENABLED = "enabled";

    /**
     * @param data
     * @param dto
     * @return
     */
    public AbstractDto getBaseDtoFromDal(Map<String, Object> data, AbstractDto dto) {
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
    public final List<AbstractDto> getDtoListFromReturnDal(List<Object> dataList, String attr) {
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
    public final List<AbstractDto> getDtoListFromDal(List<Map<String, Object>> dataList) {
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
                    .getMethod("get" + attr.substring(0, 1).toUpperCase() + attr.substring(1));
            map.put(method.invoke(abstractDto), abstractDto);
        }

        return map;
    }

    /**
     * @param data
     * @return
     */
    public abstract AbstractDto getDtoFromDal(Map<String, Object> data);
}