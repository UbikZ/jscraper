package org.ubikz.jscraper.api.core.app.entity.helper;

import org.ubikz.jscraper.api.core.app.dto.UserDto;

import java.util.Map;

public class UserEntityHelper extends AbstractEntityHelper {

    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_IS_ADMIN = "is_admin";

    /**
     * @param data
     * @return
     */
    public UserDto getDtoFromDal(Map<String, Object> data) {
        UserDto userDto = (UserDto) this.getBaseDtoFromDal(data, new UserDto());

        if (data.containsKey(COLUMN_USERNAME)) {
            userDto.setUsername((String) data.get(COLUMN_USERNAME));
        }

        if (data.containsKey(COLUMN_FIRSTNAME)) {
            userDto.setFirstname((String) data.get(COLUMN_FIRSTNAME));
        }

        if (data.containsKey(COLUMN_LASTNAME)) {
            userDto.setLastname((String) data.get(COLUMN_LASTNAME));
        }

        if (data.containsKey(COLUMN_PASSWORD)) {
            userDto.setPassword((String) data.get(COLUMN_PASSWORD));
        }

        if (data.containsKey(COLUMN_EMAIL)) {
            userDto.setEmail((String) data.get(COLUMN_EMAIL));
        }

        if (data.containsKey(COLUMN_IS_ADMIN)) {
            userDto.setAdmin((boolean) data.get(COLUMN_IS_ADMIN));
        }

        return userDto;
    }
}