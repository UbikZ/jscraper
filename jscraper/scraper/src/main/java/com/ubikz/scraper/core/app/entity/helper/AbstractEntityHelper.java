package com.ubikz.scraper.core.app.entity.helper;

import com.ubikz.scraper.core.app.dto.AbstractDto;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

public class AbstractEntityHelper {
    /**
     * @param data
     * @param dto
     * @return
     */
    public static AbstractDto getBaseDtoFromDal(Map<String, Object> data, AbstractDto dto) {
        if (data.containsKey("id")) {
            dto.setId((int) data.get("id"));
        }

        if (data.containsKey("date")) {
            Timestamp timestamp = (Timestamp) data.get("date");
            long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);
            dto.setDate(new Date(milliseconds));
        }

        if (data.containsKey("label")) {
            dto.setLabel((String) data.get("label"));
        }

        if (data.containsKey("enabled")) {
            dto.setEnabled((boolean) data.get("enabled"));
        }

        return dto;
    }
}