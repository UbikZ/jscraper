package org.ubikz.jscraper.api.entity.helper;

import org.ubikz.jscraper.api.dto.BaseDto;
import org.ubikz.jscraper.exception.ApplicativeException;
import org.ubikz.jscraper.reference.table.field.CommonReference;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseEntityHelper<D extends BaseDto> {
    protected D dto;

    public D getDtoFromDal(Map<String, Object> data) {
        if (data.containsKey(CommonReference.ID.get())) {
            dto.setId((int) data.get(CommonReference.ID.get()));
        }

        if (data.containsKey(CommonReference.DATE.get())) {
            Object date = data.get(CommonReference.DATE.get());
            if (date != null) {
                Timestamp timestamp = (Timestamp) date;
                long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);
                dto.setDate(new Date(milliseconds));
            }
        }

        if (data.containsKey(CommonReference.ENABLED.get())) {
            dto.setEnabled((Boolean) data.get(CommonReference.ENABLED.get()));
        }

        return dto;
    }

    public final List<D> getDtoListFromDal(List<Object> dataList, String attr) {
        List<D> baseDtoList = new ArrayList<>();

        dataList.forEach(element -> baseDtoList.add(getDtoFromDal(new HashMap<String, Object>() {{
            put(attr, element);
        }})));

        return baseDtoList;
    }

    public final List<D> getDtoListFromDal(List<Map<String, Object>> dataList) {
        return dataList.stream().map(this::getDtoFromDal).collect(Collectors.toList());
    }

    public Map<Object, D> getDtoMapFromDal(List<Map<String, Object>> dataList, String attr) {
        Map<Object, D> map = new HashMap<>();

        getDtoListFromDal(dataList).forEach(dto -> map.put(invokeDtoMethod(dto, attr), dto));

        return map;
    }

    private Object invokeDtoMethod(D dto, String attr) {
        try {
            Method method = dto.getClass().getMethod("get" + attr.substring(0, 1).toUpperCase() + attr.substring(1));

            return method.invoke(dto);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new ApplicativeException(e);
        }
    }
}