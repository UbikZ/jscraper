package com.ubikz.scraper.core.app.entity.helper;

import com.ubikz.scraper.core.app.dto.UserDto;

import java.util.Map;

public class UserEntityHelper extends AbstractEntityHelper {
    /**
     * @param data
     * @return
     */
    public UserDto getDtoFromDal(Map<String, Object> data) {
        UserDto userDto = (UserDto) this.getBaseDtoFromDal(data, new UserDto());

        if (data.containsKey("username")) {
            userDto.setUsername((String) data.get("username"));
        }

        if (data.containsKey("firstname")) {
            userDto.setFirstname((String) data.get("firstname"));
        }

        if (data.containsKey("lastname")) {
            userDto.setLastname((String) data.get("lastname"));
        }

        if (data.containsKey("password")) {
            userDto.setPassword((String) data.get("password"));
        }

        if (data.containsKey("email")) {
            userDto.setEmail((String) data.get("email"));
        }

        if (data.containsKey("is_admin")) {
            userDto.setAdmin((boolean) data.get("is_admin"));
        }

        return userDto;
    }
}