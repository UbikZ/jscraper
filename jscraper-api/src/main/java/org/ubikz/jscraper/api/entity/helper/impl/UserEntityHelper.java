package org.ubikz.jscraper.api.entity.helper.impl;

import org.ubikz.jscraper.api.dto.impl.UserDto;
import org.ubikz.jscraper.api.entity.helper.BaseEntityHelper;
import org.ubikz.jscraper.reference.table.field.UserReference;

import java.util.Map;

public class UserEntityHelper extends BaseEntityHelper<UserDto> {
    public UserDto getDtoFromDal(Map<String, Object> data) {
        UserDto userDto = super.getDtoFromDal(data);

        if (data.containsKey(UserReference.USERNAME.get())) {
            userDto.setUsername((String) data.get(UserReference.USERNAME.get()));
        }

        if (data.containsKey(UserReference.FIRSTNAME.get())) {
            userDto.setFirstname((String) data.get(UserReference.FIRSTNAME.get()));
        }

        if (data.containsKey(UserReference.LASTNAME.get())) {
            userDto.setLastname((String) data.get(UserReference.LASTNAME.get()));
        }

        if (data.containsKey(UserReference.PASSWORD.get())) {
            userDto.setPassword((String) data.get(UserReference.PASSWORD.get()));
        }

        if (data.containsKey(UserReference.EMAIL.get())) {
            userDto.setEmail((String) data.get(UserReference.EMAIL.get()));
        }

        if (data.containsKey(UserReference.IS_ADMIN.get())) {
            userDto.setAdmin((boolean) data.get(UserReference.IS_ADMIN.get()));
        }

        return userDto;
    }
}